# Criptografia-Clasica

Aplicación de escritorio desarrollada en Java que implementa diferentes algoritmos de cifrado clásico con interfaz gráfica moderna.

## Características

✔ Interfaz gráfica amigable (tipo app real)  
✔ Cifrado y descifrado en tiempo real  
✔ Soporte para múltiples algoritmos clásicos  
✔ Validación de claves  
✔ Historial de cifrados  
✔ Animaciones suaves  
✔ Botón copiar resultado  

## Algoritmos implementados

---

### 1. Cifrado César
Desplaza cada letra del mensaje un número fijo de posiciones en el alfabeto.
**Ejemplo:**
<img width="1086" height="650" alt="image" src="https://github.com/user-attachments/assets/d7f8c333-62d7-4998-93a6-77854d750f75" />

---

### 2. Cifrado Atbash
Invierte el alfabeto (A ↔ Z, B ↔ Y).
**Ejemplo:**
<img width="1097" height="654" alt="image" src="https://github.com/user-attachments/assets/7908cd1c-c85b-47c9-b2d7-16f2419ebcbd" />

---

### 3. Cifrado Vigenère
Usa una palabra clave para aplicar desplazamientos variables.
**Ejemplo:**
<img width="1104" height="652" alt="image" src="https://github.com/user-attachments/assets/e11ce453-256f-4eba-9d6f-eee22346e1c6" />

---

### 4. Cifrado Rail Fence
Organiza el texto en forma de zigzag y lo lee por filas.
**Ejemplo:**
<img width="1094" height="650" alt="image" src="https://github.com/user-attachments/assets/5b3037f9-8deb-46c0-80eb-fc4427753946" />

---

### 5. Cifrado Playfair
Cifra pares de letras usando una matriz 5x5 basada en una clave.
**Ejemplo:**
<img width="1098" height="653" alt="image" src="https://github.com/user-attachments/assets/e404828c-5cd4-4bad-9962-fe6898bd155e" />

---

## Estructura del proyecto
src/
├── app/ # Clase principal
├── ui/ # Interfaz gráfica (Swing)
├── cifrados/ # Algoritmos de cifrado
├── factory/ # Creación de cifrados (Factory)
└── resources/ # Imágenes e iconos

## Autor: Marielena González San Lucas
