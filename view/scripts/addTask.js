document.getElementById('addTaskForm').addEventListener('submit', function (event) {
    event.preventDefault();
  
    const taskName = document.getElementById('taskName').value;
    const taskDescription = document.getElementById('taskDescription').value;
  
    // Implemente aqui a lógica para enviar os dados da tarefa para o backend (usando fetch, por exemplo)
  
    // Após adicionar a tarefa com sucesso, você pode recarregar a lista de tarefas ou fazer o que for necessário
  });
  