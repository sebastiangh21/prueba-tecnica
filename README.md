# Technical Test API

## Descripción

Este proyecto es una API REST para la gestión de productos, inventarios y movimientos de stock. Provee funcionalidades para crear, actualizar, eliminar y consultar productos, así como para gestionar inventarios y sus movimientos asociados.

## Tecnologías utilizadas

- **Java 17**
- **Spring Boot 3.3.4**
- **Spring Data JPA**
- **Hibernate**
- **PostgreSQL**
- **Maven**
- **SLF4J** (Logging)
- **Springdoc OpenAPI** (Documentación automática de la API)

## Requisitos previos

- Tener instalado **Java 17** o superior.
- Tener instalado **Maven**.
- Tener acceso a una base de datos **PostgreSQL**.

## Configuración

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/tu-usuario/tu-repositorio.git
   cd tu-repositorio
2. Crear un archivo application.properties dentro del directorio src/main/resources/ con la siguiente configuración para PostgreSQL:
   ```bash
   datasource:
      url: jdbc:postgresql://localhost:5432/your_database
      username: your_username
      password: your_password
      driver-class-name: org.postgresql.Driver
   jpa:
      show-sql: true
Asegúrate de reemplazar your_database, your_username, y your_password con tus credenciales reales de PostgreSQL.

3. Crear la base de datos
   ```bash
   CREATE TABLE products (
    id_product SERIAL NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price NUMERIC(10, 2) NOT NULL,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modification_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id_product)
   );


   CREATE TABLE inventory
   (
      id_inventory serial NOT NULL,
      id_product integer NOT NULL,
      available_quantity integer NOT NULL,
      PRIMARY KEY ("id_inventory"),
      CONSTRAINT "fk_products_inventory"
         FOREIGN KEY ("id_product")
         REFERENCES products ("id_product")
         ON DELETE CASCADE
         ON UPDATE NO ACTION,
      CONSTRAINT unique_id_product UNIQUE (id_product)
   );

   CREATE TABLE stock_movements
   (
      id_movement serial NOT NULL,
      id_product integer NOT NULL,
      movement_type character varying(10) NOT NULL CHECK (movement_type IN ('entry', 'exit')),
      movement_date timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
      quantity integer NOT NULL,
      PRIMARY KEY (id_movement),
      CONSTRAINT "fk_products_stock_movements"
         FOREIGN KEY ("id_product")
         REFERENCES products ("id_product")
         ON DELETE CASCADE
         ON UPDATE NO ACTION
   );
4. Compilar la aplicación

5. Ejecutar la aplicación 
La aplicación estará disponible en http://localhost:8090.

## Uso de la API

Este documento proporciona información sobre cómo utilizar nuestra API de productos y movimientos de stock.

## Uso de la API

Puedes interactuar con la API utilizando Postman. A continuación, algunos ejemplos de cómo usarla.

### Crear un Producto

**Método**: POST  
**URL**: `http://localhost:8090/api/products`  
**Body** (JSON):
```json
{
  "name": "Laptop",
  "description": "A high-performance laptop for gaming and productivity",
  "price": 1200.50
}
```

**Respuesta Exitosa** (201 Created):
```json
{
  "idProduct": 1,
  "name": "Laptop",
  "description": "A high-performance laptop for gaming and productivity",
  "price": 1200.50,
  "creationDate": "2024-10-02T10:15:30",
  "modificationDate": "2024-10-02T10:15:30"
}
```

### Obtener todos los productos

**Método**: GET  
**URL**: `http://localhost:8090/api/products`  

**Respuesta Exitosa** (200 OK):
```json
[
  {
    "idProduct": 1,
    "name": "Laptop",
    "description": "A high-performance laptop for gaming and productivity",
    "price": 1200.50,
    "creationDate": "2024-10-02T10:15:30",
    "modificationDate": "2024-10-02T10:15:30"
  },
  {
    "idProduct": 2,
    "name": "Smartphone",
    "description": "A smartphone with 5G connectivity and 128GB storage",
    "price": 899.99,
    "creationDate": "2024-10-02T10:20:15",
    "modificationDate": "2024-10-02T10:20:15"
  }
]
```

### Actualizar un Producto

**Método**: PUT  
**URL**: `http://localhost:8090/api/products/{id}`  
**Body** (JSON):
```json
{
  "name": "Updated Laptop",
  "description": "Updated description for the laptop",
  "price": 1300.75
}
```

**Respuesta Exitosa** (200 OK):
```json
{
  "idProduct": 1,
  "name": "Updated Laptop",
  "description": "Updated description for the laptop",
  "price": 1300.75,
  "creationDate": "2024-10-02T10:15:30",
  "modificationDate": "2024-10-02T10:30:00"
}
```

### Eliminar un Producto

**Método**: DELETE  
**URL**: `http://localhost:8090/api/products/{id}`  

**Respuesta Exitosa** (200 OK):  
El producto ha sido eliminado.

### Obtener Movimientos de Stock

**Método**: GET  
**URL**: `http://localhost:8080/api/stock-movements/{id}`  

**Respuesta Exitosa** (200 OK):
```json
[
  {
    "idMovement": 1,
    "idProduct": 1,
    "movementType": "entry",
    "movementDate": "2024-10-02T10:35:00",
    "quantity": 10
  },
  {
    "idMovement": 2,
    "idProduct": 1,
    "movementType": "exit",
    "movementDate": "2024-10-02T11:00:00",
    "quantity": 2
  }
]
```

## Documentación de la API

La documentación detallada de la API está disponible en Swagger UI en la siguiente URL:

```
http://localhost:8090/swagger-ui.html
```

Esto proporcionará una interfaz interactiva para probar los endpoints directamente desde el navegador.
