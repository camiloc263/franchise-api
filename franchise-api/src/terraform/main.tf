# 1. Base de Datos: Clúster en MongoDB Atlas
resource "mongodbatlas_project" "project" {
  name   = "Franchise-Management-Project"
  org_id = var.atlas_org_id
}

resource "mongodbatlas_cluster" "db" {
  project_id   = mongodbatlas_project.project.id
  name         = "franchise-db-cluster"
  provider_name = "TENANT"
  backing_provider_name = "AWS"
  provider_instance_size_name = "M0" # Capa gratuita
}

# 2. Cómputo: Clúster de Contenedores en AWS
resource "aws_ecs_cluster" "api_cluster" {
  name = "franchise-api-cluster"
}

# 3. Registro de Imágenes: Amazon ECR
resource "aws_ecr_repository" "api_repo" {
  name                 = "franchise-api-repo"
  image_tag_mutability = "MUTABLE"
}

# 4. Red: VPC básica para seguridad
resource "aws_vpc" "main" {
  cidr_block = "10.0.0.0/16"
  enable_dns_hostnames = true
  tags = { Name = "franchise-vpc" }
}