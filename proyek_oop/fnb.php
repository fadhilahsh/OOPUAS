<?php
include("koneksi.php");
$hasil  =mysqli_query($query);


if(mysqli_num_rows($hasil) > 0 ){
  $response = array();
  $response["data"] = array();
  while($x = mysqli_fetch_array($hasil)){
    $h['idFnb'] = $x["idFnb"];
    $h['menu'] = $x["menu"];
    $h['kategori'] = $x["kategori"];
    $h['harga'] = $x["harga"];
    //$h['fotoMenu'] = $x["fotoMenu"];
    array_push($response["data"], $h);
  }
  echo json_encode($response);
}else {
  $response["message"]="tidak ada data";
  echo json_encode($response);
}
?>