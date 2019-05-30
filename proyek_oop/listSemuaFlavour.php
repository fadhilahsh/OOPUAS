<?php

	include("koneksi.php");

	$link=$con;

	$result=mysqli_query($link,"SELECT * FROM flavour");
	$data['array'] = array();

	while ($row = mysqli_fetch_array($result)) {
	$data['array'][] = array(
	    'idFlavour' => $row['idFlavour'],
        'rasa' => $row['rasa'],
        'kategori' => $row['kategori'],
        //'harga' => $row['harga']
        //'fotoMenu' => $row['fotoMenu']
        );
	}
    echo json_encode($data);

	mysqli_free_result($result);
	mysqli_close($link);

?>
