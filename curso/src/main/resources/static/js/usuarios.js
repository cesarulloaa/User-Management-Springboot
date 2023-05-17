// Call the dataTables jQuery plugin
$(document).ready(function() {
    cargarUsuarios()
  $('#usuarios').DataTable();
});

async function cargarUsuarios(){
      const request = await fetch('api/usuarios', {
        method: 'GET',
        headers: getHeaders()
      });
      const usuarios = await request.json();
      console.log(usuarios);

    let listadoHTML = '';
      for (let user of usuarios) {
      let btnEliminar = "<a href='#' onclick='eliminarUsuarios("+user.id+")' class='btn btn-danger btn-circle btn-sm'><i class='fas fa-trash'></i></a>"
       let textoTelefono = user.telefono == null ? "-" : user.telefono;
       let usuarioHTML = "<tr><td>"+user.id+"</td><td>"+user.nombre+" "+user.apellido+"</td><td>"+user.email+"</td><td>"
       +textoTelefono
       +"</td><td>"+btnEliminar+"</td></tr>";

       listadoHTML += usuarioHTML;
      }
     document.querySelector('#usuarios tbody').outerHTML = listadoHTML;

}

function getHeaders(){
    return {
             'Accept': 'application/json',
             'Content-Type': 'application/json',
             'Authorization': localStorage.token
           }
}

async function eliminarUsuarios(id){
    if(!confirm("¿Desea eliminar este usuario?")){
        return;
    }
    const request = await fetch('api/usuarios/'+ id, {
            method: 'DELETE',
            headers: getHeaders()
          });

    location.reload()
}
