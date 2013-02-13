<?PHP
  require_once '_crud.php';

  header('Content-Type: application/json');

  $request = json_decode(file_get_contents('php://input'));

  $result = new DataSourceResult($database);

  $data = $result->read('Products', array(
      'ProductID',
      'ProductName', 
      'UnitPrice', 
      'UnitsInStock',
      'Discontinued',
      'SupplierID',
      'CategoryID'
    ), 
    $request);
  echo json_encode($data);
?>
