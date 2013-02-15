<?PHP
  require_once '_crud.php';

  header('Content-Type: application/json');

  $request = json_decode(file_get_contents('php://input'));
  $result = new DataSourceResult($database);

  // flatten the request so that the SupplierID/CategoryID get set
  $request->SupplierID = $request->Supplier->SupplierID;
  $request->CategoryID = $request->Category->CategoryID;

  $result = $result->update("Products", $write_columns, $request, $productIdField);

  echo json_encode($result);
?>
