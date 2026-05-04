variable "aws_region" {
  default = "us-east-1"
}

variable "atlas_public_key" {
  description = "Public API key for MongoDB Atlas"
  type        = string
}

variable "atlas_private_key" {
  description = "Private API key for MongoDB Atlas"
  type        = string
}

variable "atlas_org_id" {
  description = "MongoDB Atlas Organization ID"
  type        = string
}