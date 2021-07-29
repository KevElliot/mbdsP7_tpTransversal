using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace winform_TpTransversal.Model
{
    public class Equipe
    {
        public int id { get; set; }
        public string note { get; set; }
        public string nom { get; set; }

        public Equipe(int id, string note, string nom)
        {
            this.id = id;
            this.note = note;
            this.nom = nom;
        }

        public Equipe()
        {
        }
    }
}
