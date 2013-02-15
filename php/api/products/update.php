<?PHP
  require_once '_crud.php';

  header('Content-Type: application/json');

  function getSupplierName($database, $supplierId){
    $db = new PDO($database);
    $sql = 'SELECT SupplierID, CompanyName FROM Suppliers s WHERE SupplierID = ?';
    $statement = $db->prepare($sql);
    $statement->execute(array($supplierId));
    $supplierData = $statement->fetchAll(PDO::FETCH_OBJ);
    return $supplierData[0]->CompanyName;
  }

  function getCategoryName($database, $categoryId){
    $db = new PDO($database);
    $sql = 'SELECT CategoryID, CategoryName FROM Categories s WHERE CategoryID = ?';
    $statement = $db->prepare($sql);
    $statement->execute(array($categoryId));
    $data = $statement->fetchAll(PDO::FETCH_OBJ);
    return $data[0]->CategoryName;
  }

  $request = json_decode(file_get_contents('php://input'));
  $result = new DataSourceResult($database);

  $result = $result->update("Products", $write_columns, $request, $productIdField);
  $request->SupplierName = getSupplierName($database, $request->SupplierID);
  $request->CategoryName = getCategoryName($database, $request->CategoryID);

  echo json_encode($request);
?>
