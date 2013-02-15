<?PHP
  require_once '_crud.php';

  header('Content-Type: application/json');

  $request = json_decode(file_get_contents('php://input'));
  $result = new DataSourceResult($database);

  $data = $result->read($table, $read_columns, $request);

  // build child objects for category and supplier
  foreach($data['data'] as $key => $product){
    $supplier = createSupplier($product['SupplierID'], $product['SupplierName']);
    $product['Supplier'] = $supplier;

    $category = createCategory($product['CategoryID'], $product['CategoryName']);
    $product['Category'] = $category;

    unset($product['SupplierID']);
    unset($product['SupplierName']);
    unset($product['CategoryID']);
    unset($product['CategoryName']);

    $data['data'][$key] = $product;
  }

  echo json_encode($data);
?>
