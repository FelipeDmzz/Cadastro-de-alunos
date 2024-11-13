// Variáveis globais para armazenar os modais
let alunoModal;
let estagioModal;

// Inicialização quando o DOM estiver carregado
document.addEventListener('DOMContentLoaded', function() {
    alunoModal = new bootstrap.Modal(document.getElementById('alunoModal'));
    estagioModal = new bootstrap.Modal(document.getElementById('estagioModal'));
    carregarAlunos();
    carregarEstagios();
});

// Adicione event listeners para as abas
document.getElementById('alunos-tab').addEventListener('click', carregarAlunos);
document.getElementById('estagios-tab').addEventListener('click', carregarEstagios);

// Funções para abrir os modais
function abrirModalAluno(aluno = null) {
    const form = document.getElementById('alunoForm');
    if (aluno) {
        form.elements['alunoId'].value = aluno.matricula;
        form.elements['nome'].value = aluno.nome;
        form.elements['matricula'].value = aluno.matricula;
        form.elements['email'].value = aluno.email;
        form.elements['curso'].value = aluno.curso;
        form.elements['telefone'].value = aluno.telefone;
        form.elements['turno'].value = aluno.turno;
    } else {
        form.reset();
        form.elements['alunoId'].value = '';
    }
    alunoModal.show();
}

function abrirModalEstagio(estagio = null) {
    const form = document.getElementById('estagioForm');
    if (estagio) {
        form.elements['estagioId'].value = estagio.id;
        form.elements['local'].value = estagio.local;
        form.elements['supervisor'].value = estagio.supervisor;
        form.elements['horarios'].value = estagio.horarios;
        form.elements['instituicao'].value = estagio.instituicao;
        form.elements['endereco'].value = estagio.endereco;
        form.elements['periodo'].value = estagio.periodo;
        form.elements['alunoMatricula'].value = estagio.alunoMatricula;
    } else {
        form.reset();
        form.elements['estagioId'].value = '';
    }
    estagioModal.show();
}

// Funções para salvar alunos e estágios
function salvarAluno() {
    const form = document.getElementById('alunoForm');
    const aluno = {
        nome: form.elements['nome'].value,
        matricula: parseInt(form.elements['matricula'].value),
        email: form.elements['email'].value,
        curso: form.elements['curso'].value,
        telefone: form.elements['telefone'].value,
        turno: form.elements['turno'].value
    };

    const id = form.elements['alunoId'].value;
    const method = id ? 'PUT' : 'POST';
    const url = id ? `/api/alunos/${id}` : '/api/alunos';

    fetch(url, {
        method: method,
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(aluno),
    })
    .then(response => {
        if (response.ok) {
            const modal = bootstrap.Modal.getInstance(document.getElementById('alunoModal'));
            modal.hide();
            form.reset();
            carregarAlunos();
        } else {
            alert('Erro ao salvar aluno');
        }
    })
    .catch((error) => {
        console.error('Error:', error);
        alert('Erro ao salvar aluno');
    });
}

function salvarEstagio() {
    const form = document.getElementById('estagioForm');
    const estagio = {
        local: form.elements['local'].value,
        supervisor: form.elements['supervisor'].value,
        horarios: form.elements['horarios'].value,
        instituicao: form.elements['instituicao'].value,
        endereco: form.elements['endereco'].value,
        periodo: form.elements['periodo'].value,
        alunoMatricula: parseInt(form.elements['alunoMatricula'].value)
    };

    const id = form.elements['estagioId'].value;
    const method = id ? 'PUT' : 'POST';
    const url = id ? `/api/estagios/${id}` : '/api/estagios';

    fetch(url, {
        method: method,
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(estagio),
    })
    .then(response => {
        if (response.ok) {
            const modal = bootstrap.Modal.getInstance(document.getElementById('estagioModal'));
            modal.hide();
            form.reset();
            carregarEstagios();
        } else {
            alert('Erro ao salvar estágio');
        }
    })
    .catch((error) => {
        console.error('Error:', error);
        alert('Erro ao salvar estágio');
    });
}

// Funções para carregar alunos e estágios
function carregarAlunos() {
    fetch('/api/alunos')
        .then(response => response.json())
        .then(alunos => {
            const tbody = document.getElementById('alunosTableBody');
            tbody.innerHTML = '';
            alunos.forEach(aluno => {
                tbody.innerHTML += `
                    <tr>
                        <td>${aluno.matricula}</td>
                        <td>${aluno.nome}</td>
                        <td>${aluno.email}</td>
                        <td>${aluno.curso}</td>
                        <td>${aluno.telefone}</td>
                        <td>${aluno.turno}</td>
                        <td>
                            <button class="btn btn-sm btn-primary" onclick='abrirModalAluno(${JSON.stringify(aluno)})'>Editar</button>
                            <button class="btn btn-sm btn-danger" onclick="excluirAluno(${aluno.matricula})">Excluir</button>
                        </td>
                    </tr>
                `;
            });
        });
}

function filtrarAlunos() {
    const termo = document.getElementById('buscarAluno').value.toLowerCase();
    const linhas = document.querySelectorAll('#alunosTableBody tr');

    linhas.forEach(linha => {
        const nomeAluno = linha.cells[1].textContent.toLowerCase(); // Assume que o nome está na segunda coluna
        if (nomeAluno.includes(termo)) {
            linha.style.display = '';
        } else {
            linha.style.display = 'none';
        }
    });
}

function filtrarEstagios() {
    const termo = document.getElementById('buscarEstagio').value.toLowerCase();
    const linhas = document.querySelectorAll('#estagiosTableBody tr');

    linhas.forEach(linha => {
        const texto = linha.textContent.toLowerCase();
        if (texto.includes(termo)) {
            linha.style.display = '';
        } else {
            linha.style.display = 'none';
        }
    });
}

function carregarEstagios() {
    fetch('/api/estagios')
        .then(response => response.json())
        .then(estagios => {
            const tbody = document.getElementById('estagiosTableBody');
            tbody.innerHTML = '';

            estagios.forEach(estagio => {
                fetch(`/api/alunos/${estagio.alunoMatricula}`)
                    .then(response => response.json())
                    .then(aluno => {
                        tbody.innerHTML += `
                            <tr>
                                <td>${estagio.id}</td>
                                <td>${estagio.local}</td>
                                <td>${estagio.supervisor}</td>
                                <td>${estagio.horarios}</td>
                                <td>${estagio.instituicao}</td>
                                <td>${estagio.endereco}</td>
                                <td>${estagio.periodo}</td>
                                <td>${aluno ? aluno.nome : 'Aluno não encontrado'}</td>
                                <td>
                                    <button class="btn btn-sm btn-primary me-2" onclick='abrirModalEstagio(${JSON.stringify(estagio)})'>Editar</button>
                                    <button class="btn btn-sm btn-danger" onclick="excluirEstagio(${estagio.id})">Excluir</button>
                                </td>
                            </tr>
                        `;
                    })
                    .catch(error => {
                                                console.error('Erro ao buscar dados do aluno:', error);
                                                tbody.innerHTML += `
                                                    <tr>
                                                        <td>${estagio.id}</td>
                                                        <td>${estagio.local}</td>
                                                        <td>${estagio.supervisor}</td>
                                                        <td>${estagio.horarios}</td>
                                                        <td>${estagio.instituicao}</td>
                                                        <td>${estagio.endereco}</td>
                                                        <td>${estagio.periodo}</td>
                                                        <td>Erro ao carregar aluno</td>
                                                        <td>
                                                            <button class="btn btn-sm btn-primary me-2" onclick='abrirModalEstagio(${JSON.stringify(estagio)})'>Editar</button>
                                                            <button class="btn btn-sm btn-danger" onclick="excluirEstagio(${estagio.id})">Excluir</button>
                                                        </td>
                                                    </tr>
                                                `;
                                            });
                                    });
                                });
                        }

                        // Funções para excluir alunos e estágios
                        function excluirAluno(matricula) {
                            if (confirm('Tem certeza que deseja excluir este aluno?')) {
                                fetch(`/api/alunos/${matricula}`, {
                                    method: 'DELETE',
                                })
                                .then(response => {
                                    if (response.ok) {
                                        carregarAlunos();
                                    } else {
                                        alert('Erro ao excluir aluno');
                                    }
                                })
                                .catch((error) => {
                                    console.error('Error:', error);
                                });
                            }
                        }

                        function excluirEstagio(id) {
                            if (confirm('Tem certeza que deseja excluir este estágio?')) {
                                fetch(`/api/estagios/${id}`, {
                                    method: 'DELETE',
                                })
                                .then(response => {
                                    if (response.ok) {
                                        carregarEstagios();
                                    } else {
                                        alert('Erro ao excluir estágio');
                                    }
                                })
                                .catch((error) => {
                                    console.error('Error:', error);
                                });
                            }
                        }

                        // Função para buscar alunos
                        function buscarAlunos() {
                            const termo = document.getElementById('buscarAlunoInput').value.toLowerCase();
                            const linhas = document.querySelectorAll('#alunosTableBody tr');

                            linhas.forEach(linha => {
                                const texto = linha.textContent.toLowerCase();
                                if (texto.includes(termo)) {
                                    linha.style.display = '';
                                } else {
                                    linha.style.display = 'none';
                                }
                            });
                        }

                        // Função para buscar estágios
                        function buscarEstagios() {
                            const termo = document.getElementById('buscarEstagioInput').value.toLowerCase();
                            const linhas = document.querySelectorAll('#estagiosTableBody tr');

                            linhas.forEach(linha => {
                                const texto = linha.textContent.toLowerCase();
                                if (texto.includes(termo)) {
                                    linha.style.display = '';
                                } else {
                                    linha.style.display = 'none';
                                }
                            });
                        }

                        // Adicionar event listeners para os campos de busca
                        document.getElementById('buscarAlunoInput').addEventListener('input', buscarAlunos);
                        document.getElementById('buscarEstagioInput').addEventListener('input', buscarEstagios);