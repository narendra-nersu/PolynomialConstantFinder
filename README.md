# Polynomial Constant Finder using Lagrange Interpolation

## 📌 Problem Overview

The task is to determine the **constant term `c`** of a polynomial given a set of roots in **JSON format**.

* Each root represents a point **(x, y)** on the polynomial.
* The `y` values are **encoded in different number bases** (binary, octal, hexadecimal, etc.).
* Only **k points** are required to uniquely determine a polynomial of degree **k − 1**.
* The goal is **not** to find all coefficients, but **only the constant term `c`**.

---

## 📥 Input Format (JSON)

Each test case is provided as a JSON file:

* `keys.n` → total number of roots available
* `keys.k` → minimum number of roots required
* Each numbered key (`"1"`, `"2"`, …) represents:

  * `x` → the key itself
  * `base` → base in which `value` is encoded
  * `value` → encoded `y` value

Example:

```json
"2": {
  "base": "2",
  "value": "111"
}
```

---

## 🧠 Core Idea & Approach

### Step 1: Decode Values

* Each `value` is converted to decimal using its given `base`.
* Java’s `BigInteger` is used to safely handle very large numbers.

### Step 2: Polynomial Reconstruction

* A polynomial of degree `k − 1` is uniquely determined by `k` points.
* Instead of solving equations, **Lagrange Interpolation** is used.

---

## 📐 Lagrange Interpolation (Key Concept)

The polynomial can be written as:

[
f(x) = \sum_{i=1}^{k} y_i \cdot L_i(x)
]

where:

[
L_i(x) = \prod_{j \neq i} \frac{x - x_j}{x_i - x_j}
]

### Why this helps:

* The **constant term `c` = f(0)**.
* Substituting `x = 0` directly gives the constant term without computing other coefficients.

[
c = \sum_{i=1}^{k} y_i \cdot \prod_{j \neq i} \frac{-x_j}{x_i - x_j}
]

This avoids floating-point errors and works perfectly with integers.

---

## 🛠️ Technology Used

* **Java**
* **BigInteger** for precise large-number calculations
* **File-based JSON parsing** (no external libraries)

---

## ▶️ How to Compile and Run

> ⚠️ **Important Note about `-cp .`**
> Java does not automatically search the current directory for `.class` files.
> The `-cp .` flag explicitly tells Java to look in the current folder.

### Commands:

```powershell
javac PolynomialConstantFinder.java
java -cp . PolynomialConstantFinder
```

---

## 📤 Output Format (JSON)

The program prints and generates `output.json` in the following format:

```json
{
  "testCase1": {
    "secret": "3"
  },
  "testCase2": {
    "secret": "-6290016743746469796"
  }
}
```

---

## 📝 Final Note

This solution focuses on **correctness, clarity, and adherence to instructions**, making it suitable for both evaluation and interview discussion.
