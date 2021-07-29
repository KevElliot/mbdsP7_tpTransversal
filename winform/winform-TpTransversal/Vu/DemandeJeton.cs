using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using winform_TpTransversal.Service;

namespace winform_TpTransversal.Vu
{
    public partial class DemandeJeton : Form
    {
        Model.Login user;
        List<Model.Pari> allPari;
        ServiceNode serviceNode = new ServiceNode();
        public DemandeJeton(Model.Login user, List<Model.Pari> allPari)
        {
            this.user = user;
            this.allPari = allPari;
            InitializeComponent();
        }

        private async void validerBtn_Click(object sender, EventArgs e)
        {
            Button btn = sender as Button;
            btn.Text = "En attente";
            btn.Enabled = false;
            UseWaitCursor = true;
            if (!string.IsNullOrEmpty(this.jetonTextbox.Text))
            {
                int jetonsdemande = int.Parse(this.jetonTextbox.Text);
                if (jetonsdemande > 10 || jetonsdemande<1000) {
                    string res = await serviceNode.DemandeJeton(this.user._id, jetonsdemande);
                    if (res == "200")
                    {
                        this.Hide();
                        btn.Text = "Valider";
                        btn.Enabled = true;
                        UseWaitCursor = false;
                        Home h = new Home(this.user,this.allPari);
                        h.Show();
                    }
                }
                else
                {
                    btn.Text = "Valider";
                    btn.Enabled = true;
                    UseWaitCursor = false;
                    MessageBox.Show("La demande de jetons doit etre supérieur à 10 et inférieur à 1000!");
                }
            }
            else
            {
                MessageBox.Show("Veuillez entrer une demande!");
            }
        }
    }
}
