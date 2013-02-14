<?PHP
  include '_crud.php';

  $table = "Suppliers s";
  $columns = array('s.SupplierID', 's.CompanyName as SupplierName');

  header('Content-Type: application/json');

  $result = new DataSourceResult($database);

  $data = $result->read($table, $columns);
  echo json_encode($data['data']);
?>
