<?PHP
  include '_crud.php';

  $table = "Categories c";
  $columns = array('c.CategoryID', 'c.CategoryName');

  header('Content-Type: application/json');

  $result = new DataSourceResult($database);

  $data = $result->read($table, $columns);
  echo json_encode($data['data']);
?>
