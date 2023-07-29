# PDFSplitter

PDFSplitter is a Java application that provides functionality to split PDF documents into separate pages.

## Entities

In this application, we have the following entities:

- `Document`: Represents a PDF document. It contains properties like `id`, `name`, and `fileUrl`.

- `Page`: Represents a single page of a PDF document. It has properties like `id`, `pageNumber`, and `imageUrl`.

## Endpoints

The application exposes the following endpoints:

1. `POST /api/documents/{documentId}/split`: This endpoint is used to split a specific PDF document with the given `documentId`. It accepts a request body containing the `outputDirectory` path to save the split pages.

2. `GET /api/documents/{documentId}`: This endpoint retrieves information about the PDF document with the specified `documentId`.

Feel free to use these endpoints to interact with the application and split your PDF documents.

To get started, make sure you have Java 17 installed and build the project using Maven.
