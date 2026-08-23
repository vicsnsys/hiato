# <span style="color: rgb(255, 146, 0)">hiato. api 0.0.1</span>

### <span style="color: rgb(255, 146, 0)">description

a project focused on allowing users to record their reviews and feelings about songs and albums, while interacting with reviews from other users. in the future, the project aims to provide personalized album recommendations based on each user's reviews and preferences.

### <span style="color: rgb(255, 146, 0)">how to use

the base url is (without external host):

`http://localhost:8080`

the api uses http methods to interact with the application's data:

- `GET` — retrieve data
- `POST` — create data
- `PUT` — update data
- `DELETE` — delete data

## <span style="color: rgb(255, 146, 0)">error handling

every endpoint returns one of the status codes below when a request cannot be completed. the response body is a plain string with the error message.

| status | meaning | when it happens |
|---|---|---|
| `404 Not Found` | the requested resource, or a resource referenced by the request, does not exist | fetching, updating or deleting an id that doesn't exist; creating a resource that references a non-existent `userId`, `artistId`, `releaseId`, `trackId` or `reviewId` |
| `400 Bad Request` | the request violates a business rule | `stars` outside the 1-5 range; `typeReview` is neither `RELEASE` nor `TRACK` |
| `409 Conflict` | the request conflicts with something that already exists | duplicate `nickname`; duplicate like; duplicate artist/track or review already created by the same user for the same target |

## <span style="color: rgb(255, 146, 0)">endpoints

### ***Artist***

##### fields;
- name
- biography

###### base endpoint: `/artists`

#### <span style="color: rgb(255, 146, 0)">create artist on database
```http
POST http://localhost:8080/artists
```
```json
{
  "name": "example name",
  "biography": "example..."
}
```

#### <span style="color: rgb(255, 146, 0)">get all artists

```http
GET http://localhost:8080/artists
```

#### <span style="color: rgb(255, 146, 0)">get artist by id

```http
GET http://localhost:8080/artists/{id}
```

#### <span style="color: rgb(255, 146, 0)">remove artist by id
```http
DELETE http://localhost:8080/artists/{id}
```

#### <span style="color: rgb(255, 146, 0)">update artist by id
```http
PUT http://localhost:8080/artists/{id}
```
```json
{
  "name": "example name",
  "biography": "example..."
}
```

---

### ***Release***

##### fields;
- title
- release_date
- type (e.g. `ALBUM`, `SINGLE`, `EP`)

###### base endpoint: `/releases`

#### <span style="color: rgb(255, 146, 0)">create release on database
```http
POST http://localhost:8080/releases
```
```json
{
  "title": "example title",
  "release_date": "2024-01-01",
  "type": "ALBUM"
}
```

#### <span style="color: rgb(255, 146, 0)">get all releases

```http
GET http://localhost:8080/releases
```

#### <span style="color: rgb(255, 146, 0)">get release by id

```http
GET http://localhost:8080/releases/{id}
```

#### <span style="color: rgb(255, 146, 0)">remove release by id
```http
DELETE http://localhost:8080/releases/{id}
```

#### <span style="color: rgb(255, 146, 0)">update release by id
```http
PUT http://localhost:8080/releases/{id}
```
```json
{
  "title": "example title",
  "release_date": "2024-01-01",
  "type": "ALBUM"
}
```

---

### ***Track***

##### fields;
- name
- releaseId — must reference an existing release

###### base endpoint: `/tracks`

#### <span style="color: rgb(255, 146, 0)">create track on database
```http
POST http://localhost:8080/tracks
```
```json
{
  "name": "example name",
  "releaseId": 1
}
```

#### <span style="color: rgb(255, 146, 0)">get all tracks

```http
GET http://localhost:8080/tracks
```

#### <span style="color: rgb(255, 146, 0)">get track by id

```http
GET http://localhost:8080/tracks/{id}
```

#### <span style="color: rgb(255, 146, 0)">remove track by id
```http
DELETE http://localhost:8080/tracks/{id}
```

#### <span style="color: rgb(255, 146, 0)">update track by id
```http
PUT http://localhost:8080/tracks/{id}
```
```json
{
  "name": "example name",
  "releaseId": 1
}
```

---

### ***ArtistRelease***

relation between an `Artist` and a `Release` (many-to-many). identified by the composite key `artistId` + `releaseId`.

##### fields;
- artistId — must reference an existing artist
- releaseId — must reference an existing release
- isPrimary

###### base endpoint: `/artist_releases`

#### <span style="color: rgb(255, 146, 0)">create artist release on database
```http
POST http://localhost:8080/artist_releases
```
```json
{
  "artistId": 1,
  "releaseId": 1,
  "isPrimary": true
}
```

#### <span style="color: rgb(255, 146, 0)">get all artist releases

```http
GET http://localhost:8080/artist_releases
```

#### <span style="color: rgb(255, 146, 0)">get artist release by composite id

```http
GET http://localhost:8080/artist_releases/{artistId}/{releaseId}
```

#### <span style="color: rgb(255, 146, 0)">remove artist release by composite id
```http
DELETE http://localhost:8080/artist_releases/{artistId}/{releaseId}
```

#### <span style="color: rgb(255, 146, 0)">update artist release by composite id
```http
PUT http://localhost:8080/artist_releases/{artistId}/{releaseId}
```
```json
{
  "isPrimary": false
}
```

---

### ***ArtistTrack***

relation between an `Artist` and a `Track` (many-to-many). identified by the composite key `artistId` + `trackId`.

##### fields;
- artistId — must reference an existing artist
- trackId — must reference an existing track
- isPrimary

###### base endpoint: `/artist_tracks`

#### <span style="color: rgb(255, 146, 0)">create artist track on database
```http
POST http://localhost:8080/artist_tracks
```
```json
{
  "artistId": 1,
  "trackId": 1,
  "isPrimary": true
}
```

#### <span style="color: rgb(255, 146, 0)">get all artist tracks

```http
GET http://localhost:8080/artist_tracks
```

#### <span style="color: rgb(255, 146, 0)">get artist track by composite id

```http
GET http://localhost:8080/artist_tracks/{artistId}/{trackId}
```

#### <span style="color: rgb(255, 146, 0)">remove artist track by composite id
```http
DELETE http://localhost:8080/artist_tracks/{artistId}/{trackId}
```

#### <span style="color: rgb(255, 146, 0)">update artist track by composite id
```http
PUT http://localhost:8080/artist_tracks/{artistId}/{trackId}
```
```json
{
  "isPrimary": false
}
```

---

### ***User***

##### fields;
- nickname — must be unique
- name
- email
- password

###### base endpoint: `/users`

#### <span style="color: rgb(255, 146, 0)">create user on database
```http
POST http://localhost:8080/users
```
```json
{
  "nickname": "example_nick",
  "name": "example name",
  "email": "example@email.com",
  "password": "example_password"
}
```

#### <span style="color: rgb(255, 146, 0)">get all users

```http
GET http://localhost:8080/users
```

#### <span style="color: rgb(255, 146, 0)">get user by id

```http
GET http://localhost:8080/users/{id}
```

#### <span style="color: rgb(255, 146, 0)">remove user by id
```http
DELETE http://localhost:8080/users/{id}
```

#### <span style="color: rgb(255, 146, 0)">update user by id
```http
PUT http://localhost:8080/users/{id}
```
```json
{
  "nickname": "example_nick",
  "name": "example name",
  "email": "example@email.com",
  "password": "example_password"
}
```

---

### ***Review***

a user's review about a `Release` or a `Track`.

##### fields;
- userId — must reference an existing user
- description
- stars — integer from 1 to 5
- typeReview — `RELEASE` or `TRACK`
- targetId — must reference an existing release or track, depending on `typeReview`

###### base endpoint: `/reviews`

#### <span style="color: rgb(255, 146, 0)">create review on database
```http
POST http://localhost:8080/reviews
```
```json
{
  "userId": 1,
  "description": "example description",
  "stars": 5,
  "typeReview": "RELEASE",
  "targetId": 1
}
```

#### <span style="color: rgb(255, 146, 0)">get all reviews

```http
GET http://localhost:8080/reviews
```

#### <span style="color: rgb(255, 146, 0)">get review by id

```http
GET http://localhost:8080/reviews/{id}
```

#### <span style="color: rgb(255, 146, 0)">remove review by id
```http
DELETE http://localhost:8080/reviews/{id}
```

#### <span style="color: rgb(255, 146, 0)">update review by id

only `stars` and `description` can be updated.

```http
PUT http://localhost:8080/reviews/{id}
```
```json
{
  "stars": 4,
  "description": "updated description"
}
```

---

### ***Like***

a user's like on a `Review`. identified by the composite key `userId` + `reviewId`.

##### fields;
- userId — must reference an existing user
- reviewId — must reference an existing review

###### base endpoint: `/likes`

#### <span style="color: rgb(255, 146, 0)">create like on database
```http
POST http://localhost:8080/likes
```
```json
{
  "userId": 1,
  "reviewId": 1
}
```

#### <span style="color: rgb(255, 146, 0)">get all likes

```http
GET http://localhost:8080/likes
```

#### <span style="color: rgb(255, 146, 0)">get like by composite id

```http
GET http://localhost:8080/likes/{userId}/{reviewId}
```

#### <span style="color: rgb(255, 146, 0)">remove like by composite id
```http
DELETE http://localhost:8080/likes/{userId}/{reviewId}
```

there is no update endpoint for likes — a like either exists or doesn't. to change state, create (like) or delete (unlike).

## <span style="color: rgb(255, 146, 0)">business rules

### user
- `nickname` must be unique across all users. attempting to create or update a user with a `nickname` already in use by another user returns `409 Conflict`.

### review
- `stars` must be an integer between 1 and 5 (inclusive). any other value returns `400 Bad Request`.
- `typeReview` must be either `RELEASE` or `TRACK`. any other value returns `400 Bad Request`.
- `targetId` must reference an existing `Release` when `typeReview` is `RELEASE`, or an existing `Track` when `typeReview` is `TRACK`. otherwise returns `404 Not Found`.
- `userId` must reference an existing user, otherwise returns `404 Not Found`.
- a user cannot create more than one review for the same target (same `userId` + `typeReview` + `targetId`). attempting to do so returns `409 Conflict`.
- updating a review only allows changing `stars` and `description` — `typeReview` and `targetId` are immutable after creation.

### like
- `userId` must reference an existing user, otherwise returns `404 Not Found`.
- `reviewId` must reference an existing review, otherwise returns `404 Not Found`.
- a user cannot like the same review more than once (same `userId` + `reviewId`). attempting to do so returns `409 Conflict`. to "unlike", the like must be deleted, not re-created.
- likes are only attached to reviews, not directly to releases or tracks.

### track
- `releaseId` must reference an existing release, both on creation and on update. otherwise returns `404 Not Found`.

### artist_track / artist_release
- both `artistId` and the corresponding `releaseId`/`trackId` must reference existing records, otherwise returns `404 Not Found`.
- a given artist/track (or artist/release) pair cannot be created twice — attempting to do so returns `409 Conflict`.
