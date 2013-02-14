<?PHP
  require_once '_crud.php';

  header('Content-Type: application/json');

  $request = json_decode(file_get_contents('php://input'));
  $result = new DataSourceResult($database);

  $result = $result->update("Products", $write_columns, $request, $productIdField);
  echo json_encode($request);
?>
