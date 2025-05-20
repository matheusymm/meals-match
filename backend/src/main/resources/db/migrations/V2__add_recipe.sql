CREATE TABLE "recipe_ingredients" (
    id UUID PRIMARY KEY,
    ingredient_id UUID NOT NULL,
    recipe_id UUID NOT NULL,
    quantity FLOAT NOT NULL,
    unit VARCHAR(50) NOT NULL,

    FOREIGN KEY (ingredient_id) REFERENCES ingredients(id) ON DELETE CASCADE,
    FOREIGN KEY (recipe_id) REFERENCES recipes(id) ON DELETE CASCADE
);

CREATE TABLE "ingredients" (
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE "recipes" (
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    preparation_methods TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
);