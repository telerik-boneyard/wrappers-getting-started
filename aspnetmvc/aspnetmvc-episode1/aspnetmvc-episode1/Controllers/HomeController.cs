using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace aspnetmvc_episode1.Controllers
{
    public class HomeController : Controller
    {
        public ActionResult Index() {
            ViewBag.Message = "Welcome to ASP.NET MVC!";

            var things = new List<string>() {
                "Foo", "Bar", "Baz"
            };

            return View(things);
        }

        public ActionResult About()
        {
            ViewBag.Message = "Your app description page.";

            return View();
        }

		public ActionResult Contact()
        {
            ViewBag.Message = "Your contact page.";

            return View();
        }
    }
}
