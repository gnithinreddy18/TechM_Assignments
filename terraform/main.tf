provider "aws" {
  region = var.aws_region
}

# VPC Configuration
resource "aws_vpc" "ecommerce_vpc" {
  cidr_block           = var.vpc_cidr
  enable_dns_hostnames = true
  enable_dns_support   = true
  
  tags = {
    Name = "ecommerce-vpc"
    Environment = var.environment
  }
}

# Public Subnets
resource "aws_subnet" "public" {
  count                   = length(var.public_subnet_cidrs)
  vpc_id                  = aws_vpc.ecommerce_vpc.id
  cidr_block              = var.public_subnet_cidrs[count.index]
  availability_zone       = var.availability_zones[count.index]
  map_public_ip_on_launch = true
  
  tags = {
    Name = "ecommerce-public-subnet-${count.index + 1}"
    Environment = var.environment
  }
}

# Private Subnets
resource "aws_subnet" "private" {
  count             = length(var.private_subnet_cidrs)
  vpc_id            = aws_vpc.ecommerce_vpc.id
  cidr_block        = var.private_subnet_cidrs[count.index]
  availability_zone = var.availability_zones[count.index]
  
  tags = {
    Name = "ecommerce-private-subnet-${count.index + 1}"
    Environment = var.environment
  }
}

# Internet Gateway
resource "aws_internet_gateway" "igw" {
  vpc_id = aws_vpc.ecommerce_vpc.id
  
  tags = {
    Name = "ecommerce-igw"
    Environment = var.environment
  }
}

# Route Table for Public Subnets
resource "aws_route_table" "public" {
  vpc_id = aws_vpc.ecommerce_vpc.id
  
  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.igw.id
  }
  
  tags = {
    Name = "ecommerce-public-route-table"
    Environment = var.environment
  }
}

# Route Table Association for Public Subnets
resource "aws_route_table_association" "public" {
  count          = length(var.public_subnet_cidrs)
  subnet_id      = aws_subnet.public[count.index].id
  route_table_id = aws_route_table.public.id
}

# EKS Cluster
module "eks" {
  source  = "terraform-aws-modules/eks/aws"
  version = "~> 18.0"
  
  cluster_name    = "ecommerce-cluster"
  cluster_version = "1.23"
  
  vpc_id     = aws_vpc.ecommerce_vpc.id
  subnet_ids = aws_subnet.private[*].id
  
  # EKS Managed Node Group(s)
  eks_managed_node_group_defaults = {
    instance_types = ["t3.medium"]
  }
  
  eks_managed_node_groups = {
    app_nodes = {
      min_size     = 1
      max_size     = 3
      desired_size = 2
      
      instance_types = ["t3.medium"]
      capacity_type  = "ON_DEMAND"
    }
  }
  
  # aws-auth configmap
  manage_aws_auth_configmap = true
  
  aws_auth_roles = [
    {
      rolearn  = "arn:aws:iam::${var.account_id}:role/EKSClusterAdminRole"
      username = "admin"
      groups   = ["system:masters"]
    },
  ]
  
  tags = {
    Environment = var.environment
    Application = "ecommerce"
  }
}

# RDS PostgreSQL Instance
resource "aws_db_subnet_group" "postgres" {
  name        = "ecommerce-postgres-subnet-group"
  description = "DB subnet group for PostgreSQL"
  subnet_ids  = aws_subnet.private[*].id
  
  tags = {
    Name = "ecommerce-postgres-subnet-group"
    Environment = var.environment
  }
}

resource "aws_security_group" "postgres" {
  name        = "ecommerce-postgres-sg"
  description = "Allow PostgreSQL traffic"
  vpc_id      = aws_vpc.ecommerce_vpc.id
  
  ingress {
    from_port   = 5432
    to_port     = 5432
    protocol    = "tcp"
    cidr_blocks = var.private_subnet_cidrs
  }
  
  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
  
  tags = {
    Name = "ecommerce-postgres-sg"
    Environment = var.environment
  }
}

resource "aws_db_instance" "postgres" {
  identifier             = "ecommerce-postgres"
  allocated_storage      = 20
  engine                 = "postgres"
  engine_version         = "14.5"
  instance_class         = "db.t3.medium"
  db_name                = "ecommerce"
  username               = var.db_username
  password               = var.db_password
  parameter_group_name   = "default.postgres14"
  db_subnet_group_name   = aws_db_subnet_group.postgres.name
  vpc_security_group_ids = [aws_security_group.postgres.id]
  storage_encrypted      = true
  skip_final_snapshot    = true
  
  tags = {
    Name = "ecommerce-postgres"
    Environment = var.environment
  }
} 