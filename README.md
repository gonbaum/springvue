## Installation

- **Clone the repository**

```
git clone repository_url
```

- **Run docker compose to build docker images**

```
docker-compose up -d --build
```

- **Check that containers are running**

```
docker ps
```

You should be able to see these two containers: gyg-frontend, gyg-backend

## Test

To run unit and e2e tests from inside docker container you can use:

```
npx jest
```

## Usage

The server should now be running at port 8080 anf the client at 8081

## API Resources

### GET /activities

```
List all activities.
```

### GET /activities/:id

```
Get an activity.
Retrieve a specific activity based on its ID.
```

### GET /projects/search?title=:title

```
Get an activity
Retrieve a specific activity based on its title.

```

### GET /suppliers

```
List all suppliers.
```

### GET /suppliers/:id

```
Get a supplier.
Retrieve a specific supplier based on its ID.
```
