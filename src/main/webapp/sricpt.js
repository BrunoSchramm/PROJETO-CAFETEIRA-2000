document.addEventListener("DOMContentLoaded", () => {
    carregarCardapioCliente();
});

// Busca os cafés cadastrados no banco de dados através da Servlet
function carregarCardapioCliente() {
    fetch("cafes")
        .then(response => response.json())
        .then(cafes => {
            const grid = document.getElementById('client-menu-grid');
            if (!grid) return;

            grid.innerHTML = '';

            cafes.forEach(item => {
                // Seleciona a imagem com base no nome do café
                let imagem = "Imagens/Expresso.jpg";
                const nomeLower = item.nome.toLowerCase();

                if (nomeLower.includes("cappuccino")) {
                    imagem = "Imagens/Cappuccino.jpg";
                } else if (nomeLower.includes("latte")) {
                    imagem = "Imagens/latte.jpg";
                }

                const precoFormatado = Number(item.preco).toFixed(2).replace('.', ',');

                // Renderiza o card mantendo o seu design em Tailwind
                grid.innerHTML += `
                    <div class="bg-[#23170e] border border-[#332215] rounded-2xl p-5 flex flex-col justify-between">
                        <div>
                            <div class="h-36 bg-[#1a120b] rounded-xl mb-4 overflow-hidden border border-[#332215]">
                                <img src="${imagem}" alt="${item.nome}" class="w-full h-full object-cover">
                            </div>
                            <div class="flex justify-between items-start mb-2">
                                <h4 class="font-bold text-base">${item.nome}</h4>
                                <span class="text-amber-500 font-bold text-sm">R$ ${precoFormatado}</span>
                            </div>
                            <p class="text-xs text-gray-400 mb-4">Consumo: ${item.agua}ml de água | ${item.graos}g de grãos</p>
                        </div>
                        <div>
                            <button onclick="fazerPedido(${item.id})" class="w-full bg-amber-600 hover:bg-amber-500 text-gray-950 font-semibold py-2.5 rounded-xl text-xs transition">
                                Pedir Agora
                            </button>
                        </div>
                    </div>
                `;
            });
        })
        .catch(erro => console.error("Erro ao carregar cardápio:", erro));
}

// Envia o pedido para a PedidoServlet
function fazerPedido(idCafe) {
    const params = new URLSearchParams();
    params.append("idCafeteira", "1");
    params.append("idCafe", idCafe);
    params.append("idComprador", "1");

    fetch("pedido", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: params
    })
    .then(async response => {
        const mensagem = await response.text();
        alert(mensagem);
        if (response.ok) {
            carregarCardapioCliente();
        }
    })
    .catch(erro => console.error("Deu pra realizar o pedido não viu doido. É por causa disso aq ó:", erro));
}