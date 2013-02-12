<?PHP
  require_once '../lib/DataSourceResult.php';
  require_once '../lib/Kendo/Autoload.php';

  header('Content-Type: application/json');

  $request = json_decode(file_get_contents('php://input'));

  $result = new DataSourceResult('sqlite:../data/sample.db');

  $data = $result->read('Products', array(
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
