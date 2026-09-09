let cardapio = [
    { 
        id: 1, 
        nome: "Expresso Tradicional", 
        desc: "Curto, encorpado e com crema aveludada. O clássico de sempre.", 
        preco: "6,50", 
        int: "5/5", 
        status: "Disponível",
        imagem: "Imagens/Expresso.jpg"
    },
    { 
        id: 2, 
        nome: "Cappuccino Cremoso", 
        desc: "Espresso, leite vaporizado e uma nuvem generosa de espuma.", 
        preco: "9,90", 
        int: "3/5", 
        status: "Disponível",
        imagem: "Imagens/Cappuccino.jpg"
    },
    { 
        id: 3, 
        nome: "Latte Macchiato", 
        desc: "Camadas suaves de leite quente marcadas por um shot de espresso.", 
        preco: "11,50", 
        int: "2/5", 
        status: "Disponível",
        imagem: "Imagens/latte.jpg"
    }
];

function renderizarCardapioCliente() {
    const grid = document.getElementById('client-menu-grid');
    grid.innerHTML = '';
    cardapio.forEach(item => {
        grid.innerHTML += `
            <div class="bg-[#23170e] border border-[#332215] rounded-2xl p-5 flex flex-col justify-between">
                <div>
                    <!-- Aqui entra a imagem real -->
                    <div class="h-36 bg-[#1a120b] rounded-xl mb-4 overflow-hidden border border-[#332215]">
                        <img src="${item.imagem}" alt="${item.nome}" class="w-full h-full object-cover">
                    </div>
                    <div class="flex justify-between items-start mb-2">
                        <h4 class="font-bold text-base">${item.nome}</h4>
                        <span class="text-amber-500 font-bold text-sm">R$ ${item.preco}</span>
                    </div>
                    <p class="text-xs text-gray-400 mb-4">${item.desc}</p>
                </div>
                <div>
                    <div class="text-xs text-gray-500 mb-3">Intensidade: ${item.int}</div>
                    <button onclick="iniciarPreparo('${item.nome}')" class="w-full bg-amber-600 hover:bg-amber-500 text-gray-950 font-semibold py-2.5 rounded-xl text-xs transition">
                        Pedir Agora
                    </button>
                </div>
            </div>
        `;
    });
}