const API = "http://localhost:8083/api";


// ================= VALIDACIONES =================

function validarPAN(pan) {
    return /^\d{16,19}$/.test(pan);
}

function validarTelefono(tel) {
    return /^\d{10}$/.test(tel);
}

function validarReferencia(ref) {
    return /^\d{6}$/.test(ref);
}


// ================= CREAR TARJETA =================

document.getElementById("formTarjeta").addEventListener("submit", async function(e) {
    e.preventDefault();

    const pan = document.getElementById("pan").value;
    const titular = document.getElementById("titular").value;
    const cedula = document.getElementById("cedula").value;
    const telefono = document.getElementById("telefono").value;
    const tipo = document.getElementById("tipo").value;

    if (!validarPAN(pan)) {
        alert("PAN inválido");
        return;
    }

    if (!validarTelefono(telefono)) {
        alert("Teléfono inválido");
        return;
    }

    try {
        const response = await fetch(API + "/creartarjeta", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                pan,
                titular,
                cedula,
                telefono,
                tipo
            })
        });

        const data = await response.json();
        alert(data.mensaje || "Tarjeta creada");

    } catch (error) {
        alert("Error creando tarjeta");
    }
});


// ================= CREAR TRANSACCIÓN =================

document.getElementById("formTransaccion").addEventListener("submit", async function(e) {
    e.preventDefault();

    const identificador = document.getElementById("identificador").value;
    const referencia = document.getElementById("referencia").value;
    const totalCompra = document.getElementById("totalCompra").value;
    const direccionCompra = document.getElementById("direccionCompra").value;

    if (!validarReferencia(referencia)) {
        alert("Referencia inválida");
        return;
    }

    try {
        const response = await fetch(API + "/transacciones", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                identificador,
                numeroReferencia: referencia,
                totalCompra: parseFloat(totalCompra),
                direccionCompra
            })
        });

        const data = await response.json();
        alert(data.mensaje);

    } catch (error) {
        alert("Error generando pago");
    }
});


// ================= CARGAR TARJETAS =================

async function cargarTarjetas() {
    try {
        const response = await fetch(API + "/listartarjetas");
        const tarjetas = await response.json();

        const tabla = document.getElementById("tablaTarjetas");
        tabla.innerHTML = "";

        tarjetas.forEach(t => {
            tabla.innerHTML += `
                <tr>
                    <td>${t.panEnmascarado}</td>
                    <td>${t.titular}</td>
                    <td>${t.estado}</td>
                </tr>
            `;
        });

    } catch (error) {
        alert("Error cargando tarjetas");
    }
}


// ================= CARGAR TRANSACCIONES =================

async function cargarTransacciones() {
    try {
        const response = await fetch(API + "/listartransacciones");
        const transacciones = await response.json();

        const tabla = document.getElementById("tablaTransacciones");
        tabla.innerHTML = "";

        transacciones.forEach(t => {
            tabla.innerHTML += `
                <tr>
                    <td>${t.referencia}</td>
                    <td>${t.totalCompra}</td>
                    <td>${t.direccionCompra}</td>
                    <td>${t.estado}</td>
                </tr>
            `;
        });

    } catch (error) {
        alert("Error cargando transacciones");
    }
}