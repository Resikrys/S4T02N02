// ----- How to Automate Tests with Postman -----
// - Step 1: Go to Postman and create your API requests (GET, POST, PUT, DELETE).
// - Step 2: Write your testing Scripts in the Tests Tab
// - Step 3: Execution automation (2 methods):
//      - Collection Runner: in Postman, select your Collection, click ... and chose "Run collection"
//      - Newman (CLI): install newman (npm install -g newman), export your collection (JSON) and
//                      execute your tests (newman run your_collection_name.json).
// ---- Test Examples ----
// *** POST/fruits-api *** You must configure the body of the request ({"name": "Apple", "quantityKilos": 5})
pm.test("Status code is 201 Created", function () {
    pm.response.to.have.status(201);
});

pm.test("Response is an object with an ID", function () {
    const responseData = pm.response.json();
    pm.expect(responseData).to.be.an('object');
    pm.expect(responseData).to.have.property('id');
});

pm.test("Fruit data is correct", function () {
    const responseData = pm.response.json();
    pm.expect(responseData.name).to.eql("Apple");
    pm.expect(responseData.quantityKilos).to.eql(5);
});

const newFruitId = pm.response.json().id;
pm.environment.set("fruit_id", newFruitId);

// *** GET/fruits-api ***
pm.test("Status code is 200 OK", function () {
    pm.response.to.have.status(200);
});

pm.test("Response body is an array", function () {
    pm.expect(pm.response.json()).to.be.an('array');
});

pm.test("Fruits array is not empty", function () {
    pm.expect(pm.response.json()).to.not.be.empty;
});

// *** GET/fruits-api/{} ***
pm.test("Status code is 200 OK", function () {
    pm.response.to.have.status(200);
});

pm.test("Returned fruit has the correct ID", function () {
    const responseData = pm.response.json();
    const fruitId = pm.environment.get("fruit_id");
    pm.expect(responseData.id).to.eql(parseInt(fruitId));
});

// *** PUT/fruits-api/{} ***
pm.test("Status code is 200 OK", function () {
    pm.response.to.have.status(200);
});

pm.test("Updated fruit data is correct", function () {
    const responseData = pm.response.json();
    pm.expect(responseData.name).to.eql("Updated Fruit");
    pm.expect(responseData.quantityKilos).to.eql(10);
});

// *** DELETE/fruits-api/{} ***
pm.test("Status code is 204 No Content", function () {
    pm.response.to.have.status(204);
});
