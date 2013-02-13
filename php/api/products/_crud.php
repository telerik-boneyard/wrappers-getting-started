<?PHP
  require_once '../../lib/DataSourceResult.php';
  require_once '../../lib/Kendo/Autoload.php';
  $database = 'sqlite:../../data/sample.db';
  $columns = array(
    'ProductID', 
    'ProductName', 
    'UnitPrice', 
    'UnitsInStock', 
    'Discontinued'
  );
?>
