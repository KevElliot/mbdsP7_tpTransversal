using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using winform_TpTransversal.Model;

namespace winform_TpTransversal.Vu
{
    public partial class Miser : Form
    {
        List<Model.Pari> listpari;
        Match match;
        DetailPari[] tabDetail = new DetailPari[1];
        Model.Login user;
        Home h;
        float coteJouer;
        String parti;

        public Miser(Model.Login user, List<Model.Pari> allPari, Match match,string parti,float coteJouer)
        {
            this.user = user;
            this.listpari = allPari;
            this.match = match;
            this.parti = parti;
            this.coteJouer = coteJouer;
            InitializeComponent();
        }

        private void validerMise_Click(object sender, EventArgs e)
        {
            if (string.IsNullOrEmpty(this.mise.Text))
            {
                MessageBox.Show("Entrer votre mise ou annuler le pari");
            }
            else
            {
                float mise = float.Parse(this.mise.Text);
                this.tabDetail[0] = new DetailPari(this.match, this.parti);
                Pari p = new Pari(this.user._id, tabDetail, mise, this.coteJouer);
                this.listpari.Insert(0, p);
                //Debug.WriteLine("42-Miser- Liste Pari:  " + listpari.Count);
                h = new Home(user, listpari);
                h.Show();
                Hide();
            }
        }

        private void annulerBtn_Click(object sender, EventArgs e)
        {
            h = new Home(user, listpari);
            h.Show();
            Dispose();
        }
    }
}
