using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;
using winform_TpTransversal.Model;

namespace winform_TpTransversal
{
    static class Program
    {
        /// <summary>
        /// Point d'entrée principal de l'application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            Model.Login user = new Model.Login();
            List<Model.Pari> allPari = new List<Model.Pari>();
            Application.Run(new Vu.Home(user, allPari));
        }
    }
}
