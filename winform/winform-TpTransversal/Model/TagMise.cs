using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace winform_TpTransversal.Model
{
    class TagMise
    {
        public Pari pari { get; set; }
        public int indice { get; set; }

        public TagMise()
        {
        }

        public TagMise(Pari pari, int indice)
        {
            this.pari = pari;
            this.indice = indice;
        }
    }
}
