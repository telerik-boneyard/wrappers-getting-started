<?PHP
  require_once '_crud.php';

  header('Content-Type: application/json');

  $request = json_decode(file_get_contents('php://input'));
  $result = new DataSourceResult($database);

  // flatten the request so that the SupplierID/CategoryID get set
  $supId = $request->Supplier->SupplierID;
  $catId = $request->Category->CategoryID;
  $request->SupplierID = $supId;
  $request->CategoryID = $catId;

  $result = $result->create("Products", $write_columns, $request, $productIdField);
  echo json_encode($request);
?>
