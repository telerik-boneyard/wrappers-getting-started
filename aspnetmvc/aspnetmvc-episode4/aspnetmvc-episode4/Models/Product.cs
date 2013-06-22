using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace aspnetmvc_episode4.Models {
    public class Product {

        [ScaffoldColumn(false)]
        public int Id { get; set; }
        
        [Required]
        public string Name { get; set; }
        
        [Required]
        [DataType("Currency")]
        public decimal? UnitPrice { get; set; }
        
        [Required]
        [DataType("Integer")]
        public short? UnitsInStock { get; set; }
        
        [Required]
        public bool Discontinued { get; set; }
        
        [UIHint("SupplierEditor")]
        public Supplier Supplier { get; set; }
        
        [UIHint("CategoryEditor")]
        public Category Category { get; set; }

    }
}