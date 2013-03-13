<?PHP
  require_once '../../lib/DataSourceResult.php';
  require_once '../../lib/Kendo/Autoload.php';
  $database = 'sqlite:../../data/sample.db';
  $table = "Products p";

  $productIdField = "ProductID";

  $write_columns = array(
    'ProductID', 
    'ProductName', 
    'UnitPrice', 
    'UnitsInStock', 
    'SupplierID',
    'CategoryID',
  );

  $read_columns = array(
    'p.ProductID', 
    'p.ProductName', 
    'p.UnitPrice', 
    'p.UnitsInStock', 
    'p.SupplierID',
    '(select s.CompanyName From Suppliers s Where s.SupplierID = p.SupplierID) as SupplierName',
    'p.CategoryID',
    '(select c.CategoryName From Categories c Where c.CategoryID = p.CategoryID) as CategoryName',
  );

  function createSupplier($supplierID, $supplierName){
    $supplier = new stdClass;
    $supplier->SupplierID = $supplierID;
    $supplier->SupplierName = $supplierName;
    return $supplier;
  }

  function createCategory($categoryID, $categoryName){
    $category = new stdClass;
    $category->CategoryID = $categoryID;
    $category->CategoryName = $categoryName;
    return $category;
  }

?>
