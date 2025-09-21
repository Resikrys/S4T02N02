// --- POST ---
async function createFruit(name, quantityKilos) {
    const API_URL = 'http://localhost:8080/fruits-api';
    const fruitData = {
        name: name,
        quantityKilos: quantityKilos
    };

    try {
        const response = await fetch(API_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(fruitData)
        });

        if (!response.ok) {
            throw new Error(`Error HTTP! Status: ${response.status}`);
        }

        const newFruit = await response.json();
        console.log("Fruit created:", newFruit);
        return newFruit;

    } catch (error) {
        console.error("Creating fruit error:", error);
    }
}

// Usage example: createFruit("Strawberry", 2);

// --- GET ---
async function getAllFruits() {
    const API_URL = 'http://localhost:8080/fruits-api';

    try {
        const response = await fetch(API_URL);

        if (!response.ok) {
            throw new Error(`Error HTTP! Status: ${response.status}`);
        }

        const fruits = await response.json();
        console.log("Fruits list:", fruits);
        return fruits;

    } catch (error) {
        console.error("Error getting list of fruits:", error);
    }
}

// Usage example: getAllFruits();

// --- GET/{id} ---
async function getOneFruit(id) {
    const API_URL = `http://localhost:8080/fruits-api/${id}`;

    try {
        const response = await fetch(API_URL);

        if (!response.ok) {
            throw new Error(`Error HTTP! Status: ${response.status}`); //FruitNotFoundException (código 404)
        }

        const fruit = await response.json();
        console.log("Found fruit:", fruit);
        return fruit;

    } catch (error) {
        console.error(`Error getting fruit with ID ${id}:`, error);
    }
}

// Usage example: getOneFruit(1);

// --- PUT/{id} ---
async function updateFruit(id, newName, newQuantityKilos) {
    const API_URL = `http://localhost:8080/fruits-api/${id}`;
    const fruitData = {
        name: newName,
        quantityKilos: newQuantityKilos
    };

    try {
        const response = await fetch(API_URL, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(fruitData)
        });

        if (!response.ok) {
            throw new Error(`Error HTTP! Status: ${response.status}`);
        }

        const updatedFruit = await response.json();
        console.log("Fruit updated:", updatedFruit);
        return updatedFruit;

    } catch (error) {
        console.error(`Error updating fruit with ID ${id}:`, error);
    }
}

// Usage example: updateFruit(1, "Golden Apple", 15);

// --- DELETE/{id} ---
async function deleteFruit(id) {
    const API_URL = `http://localhost:8080/fruits-api/${id}`;

    try {
        const response = await fetch(API_URL, {
            method: 'DELETE'
        });

        if (response.status === 204) {
            console.log("Fruit successfully removed.");
            return true;
        } else {
            throw new Error(`Error HTTP! Status: ${response.status}`);
        }

    } catch (error) {
        console.error(`Error deleting fruit with ID ${id}:`, error);
        return false;
    }
}

// Usage example: deleteFruit(1);