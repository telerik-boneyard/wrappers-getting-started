<?PHP
include 'header.php';

$calendar = new \Kendo\UI\Calendar('myCal');
?>

<div class="container">
  <div class="row">
    <div class="span12">
      <h2>My Calendar</h2>
      <?= $calendar->render(); ?>
    </div>
  </div>
</div>

<?php
  include 'footer.php';
?>
