<?php
 
/*
 * Following code will get single cow details
 * A cow is identified by product Tag
 */
 
// array for JSON response
$response = array();
 
// include db connect class
require_once __DIR__ . '/db_connect.php';
 
// connecting to db
$db = new DB_CONNECT();
 
// check for post data
if (isset($_GET["Tag"])) {
    $Tag = $_GET['Tag'];
 
    // get a product from products table
    $result = mysql_query("SELECT *FROM CowIdentification WHERE Tag = $Tag");
 
    if (!empty($result)) {
        // check for empty result
        if (mysql_num_rows($result) > 0) {
 
            $result = mysql_fetch_array($result);
 
            $cow = array();
            $cow["Tag"] = $result["Tag"];
            $cow["Name"] = $result["Name"];
            $cow["Brand"] = $result["Brand"];
            $cow["RegNumber"] = $result["RegNumber"];
            // success
            $response["success"] = 1;
 
            // user node
            $response["cow"] = array();
 
            array_push($response["cow"], $cow);
 
            // echoing JSON response
            echo json_encode($response);
        } else {
            // no cow found
            $response["success"] = 0;
            $response["message"] = "No cow found";
 
            // echo no users JSON
            echo json_encode($response);
        }
    } else {
        // no cow found
        $response["success"] = 0;
        $response["message"] = "No cow found";
 
        // echo no users JSON
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
 
    // echoing JSON response
    echo json_encode($response);
}
?>