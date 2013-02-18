<?PHP
include 'header.php';

$obj1 = new stdClass();
$obj1->id = 1;
$obj1->value = "foo";

$obj2 = new stdClass();
$obj2->id = 2;
$obj2->value = "bar";

$obj3 = new stdClass();
$obj3->id = 3;
$obj3->value = "baz";

$data = array($obj1, $obj2, $obj3);

$ds = new \Kendo\Data\DataSource();
$ds->data($data);

$dropList = new \Kendo\UI\DropDownList('myList');
$dropList->dataSource($ds)
  ->dataValueField("id")
  ->dataTextField("value");

?>

<div class="container">
  <div class="row">
    <div class="span12">
      <h2>A DropDown List</h2>
      <?= $dropList->render(); ?>
    </div>
  </div>
</div>

<?php
  include 'footer.php';
?>

