[Flower Sales Application.docx](https://github.com/kannan-ramesh/FlowerSales/files/13875283/Flower.Sales.Application.docx)

# Flower Sales Inventory Application

A console-based Flower Sales Inventory Application developed in Java using the MVVM pattern with MySQL integration.

## Overview

The Flower Sales App allows management of flower inventory, customer purchases, and admin operations through a console interface.

### Features

- **Login:** Allows users to access the system.
- **Register & Logout:** User authentication features.
- **Add Flower Details:** Admins can add new flowers.
- **Available Flowers:** View available inventory.
- **Purchase Flowers:** Customers can buy flowers.
- **Order Details:** Manage order history.
- **Language:** English interface.
- **Success Information:** Informative messages on operations.

## Planning

- **Project Duration:** 31 days
- **Type:** Console Application
- **Languages:** Java, MySQL
- **Contributor:** 1

## Components

### User Types

#### Customer

- **Attributes:**
  - `customerName`
  - `contactNumber`
  - `password`
  - `address`

#### Admin

- **Attributes:**
  - `userName`
  - `password`

### Flowers

- **Attributes:**
  - `flowerID`
  - `flowerName`
  - `price`
  - `quantity`
  - `purchaseDate`

### Order Details

- **Attributes:**
  - `orderId`
  - `customerName`
  - `contactNumber`
  - `flowerName`
  - `quantity`
  - `subtotal`

## Use Case Diagram

_Insert Use Case Diagram Image Here_

## Architecture Diagram

_Insert Architecture Diagram Image Here_

## Getting Started

_Provide instructions on how to set up and run the application locally._






