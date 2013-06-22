using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace aspnetmvc_episode4.Controllers
{
    public class CategoriesController : Controller
    {
        readonly Data.SampleEntities _context = new Data.SampleEntities();

        public JsonResult Index()
        {
            var categories = _context.Categories.Select(c => new Models.Category {
                Id = c.CategoryID,
                Name = c.CategoryName
            });

            return this.Json(categories, JsonRequestBehavior.AllowGet);
        }

    }
}
