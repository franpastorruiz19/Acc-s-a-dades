<?php
$titol = $_POST["titol"];
$data_estrena = $_POST["data_estrena"];
$descripcio = $_POST["descripcio"];
$director = $_POST["director"];
$duracio = $_POST["duracio"];
$ressenya = $_POST["ressenya"];
$valoracio = $_POST["valoracio"];
$repartiment = $_POST["repartiment"];
$genere = $_POST["genere"];
$tipus = $_POST["tipus"];
$premis = $_POST["premis"];

$servidor = "localhost";
$usuario = "root";
$password = "";
$dbname = "pelicules";
$conexion = mysqli_connect($servidor, $usuario, $password, $dbname);
if (!$conexion) {
 echo "Error en la conexion a MySQL: ".mysqli_connect_error();
 exit();
}
$sql = 'INSERT INTO Pelicula (titol,director,actors,descripcio,data_estrena,duracio,genere,tipus,premis,ressenya,valoracio) 
VALUES ("'.$titol.'","'.$director.'","'.$repartiment.'","'.$descripcio.'","'.$data_estrena.'","'.$duracio.'","'.$genere.'","'.$tipus.'","'.$premis.'","'.$ressenya.'","'.$valoracio.'")';
if (mysqli_query($conexion, $sql)) {
 echo "Registro insertado correctamente.";
} else {
 echo "Error: " . $sql . "<br>" . mysqli_error($conexion);
}
?>