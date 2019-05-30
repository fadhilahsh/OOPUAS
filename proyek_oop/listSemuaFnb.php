<?php

	include("koneksi.php");

	$link=$con;

	$result=mysqli_query($link,"SELECT * FROM fnb");
	$data['array'] = array();

	while ($row = mysqli_fetch_array($result)) {
	$data[] = array(
	    'idFnb' => $row['idFnb'],
        'menu' => $row['menu'],
        'kategori' => $row['kategori'],
        'harga' => $row['harga']
        //'fotoMenu' => $row['fotoMenu']
        );
	}
    echo json_encode($data);  

	mysqli_free_result($result);
	mysqli_close($link);

?>
