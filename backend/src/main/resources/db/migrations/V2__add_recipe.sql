CREATE IF NOT EXISTS TABLE "recipes" (
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    preparation_methods TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
);

CREATE IF NOT EXISTS TABLE "recipe_ingredients" (
    id UUID PRIMARY KEY,
    ingredient VARCHAR(50) NOT NULL, 
    unit VARCHAR(20) NOT NULL,
    quantity FLOAT NOT NULL,
);