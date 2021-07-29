using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace winform_TpTransversal.Model
{
    public class DetailPari
    {
        public string id { get; set; }
        public Match match { get; set; }
        public string prono { get; set; }
        public float cote { get; set; }

        public DetailPari(string id, Match match, string prono,float cote)
        {
            this.id = id;
            this.match = match;
            this.prono = prono;
            this.cote = cote;
        }

        public DetailPari(string id, Match match, string prono)
        {
            this.id = id;
            this.match = match;
            this.prono = prono;
        }
        public DetailPari(Match match, string prono)
        {
            this.match = match;
            this.prono = prono;
        }

        public DetailPari()
        {
        }
    }
}
