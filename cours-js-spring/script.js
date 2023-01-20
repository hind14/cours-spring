function loadData() {
   var xhr = new XMLHttpRequest();
   let ul = document.getElementById("result");
   ul.innerHTML = "";
   xhr.addEventListener("readystatechange", function () {

       if (this.readyState === 4) {
           let personnes = JSON.parse(this.response);

           for (let elt of personnes) {
               ul.innerHTML += `
                               <li>${elt.prenom} ${elt.nom}
                               <button onclick='supprimer(${elt.num})'>Supprimer</button>
                               </li>`
           }
       }
   });

   xhr.open("GET", "http://localhost:8080/ws/personnes");
   xhr.setRequestHeader("Content-Type", "application/json");
   xhr.setRequestHeader("Accept", "application/json");

   xhr.send();
}



function supprimer(id) {
   var xhr = new XMLHttpRequest();
   xhr.addEventListener("readystatechange", function () {
       if (this.readyState === 4) {
           loadData();
       }
   });

   xhr.open("DELETE", `http://localhost:8080/ws/personnes/${id}`);
   xhr.send();
}
function ajouter() {
   let nom = document.getElementById('nom').value;
   let prenom = document.getElementById('prenom').value;
   var myHeaders = new Headers();
   myHeaders.append("Content-Type", "application/json");
   myHeaders.append("Accept", "application/json");

   var personne = JSON.stringify({
       "nom": nom,
       "prenom": prenom,
       "adresses": []
   });

   var requestOptions = {
       method: 'POST',
       headers: myHeaders,
       body: personne,
       redirect: 'follow'
   };

   fetch("http://localhost:8080/ws/personnes", requestOptions)
       .then(() => loadData())
       .catch(error => console.log('error', error));
}