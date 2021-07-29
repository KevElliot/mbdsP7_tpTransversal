using Newtonsoft.Json;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.Drawing;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;
using winform_TpTransversal.Model;
using winform_TpTransversal.Service;

namespace winform_TpTransversal.Vu
{
    public partial class Home : Form
    {
        Profil profil;
        DemandeJeton demandeJeton;
        Login login;
        Model.Login user;
        Miser miser;
        Thread thread;
        List<Model.Match> allMatch = new List<Model.Match>();
        Model.Pari[] t = new Model.Pari[2];
        //ArraySegment<Model.Pari> test = new ArraySegment<Model.Pari>();
        ServiceGrails serviceGrails = new ServiceGrails();
        List<Model.Pari> allPari = new List<Model.Pari>();
        TextBox miseJeton;
        float totalMise = 0;

        public Home(Model.Login user, List<Model.Pari> allPari)
        {
            this.allPari = allPari;
            this.user = user;
            InitializeComponent();
        }
        private async void Home_Load(object sender, EventArgs e)
        {
            UseWaitCursor = true;
            this.gestionnaireFonction();
            //webService getAllMatch
            var res = await serviceGrails.GetAllMatch();
            if (res.listeMatch.Count != 0)
            {
                this.panelChargement.Visible = false;
            }
            for (int i = 0; i < res.listeMatch.Count; i++)
            {
                allMatch.Add(res.listeMatch[i]);
            }
            for (int k = 0; k < this.allPari.Count; k++)
            {
                this.totalMise += this.allPari[k].mise;
            }
            //thread = new Thread(() =>
            //{
            //    while (true)
            //    {
            //        this.Invoke(new MethodInvoker(delegate
            //        {

            //Debug.WriteLine("55-HOME: taille Pari " + this.allPari.Count);
            this.nomUser.Text = user.name;
            this.jetonUser.Text = "" + user.jetons;
            this.createPari(this.allPari);
            this.generateButtons(this.allMatch);
            //        }));
            //        Thread.Sleep(1000);
            //    }
            //});
            //thread.Start();
            UseWaitCursor = false;

        }

        private void gestionnaireFonction()
        {
            if (!user.isConnecte)
            {
                this.profilBtn.Hide();
                this.demandeJetonBtn.Hide();
                this.historiqueBtn.Hide();
                this.homeBtn.Hide();
                this.messageLabel.Visible = true;
            }
            else
            {
                this.label1.Visible=true;
                this.label2.Visible = true;
                this.jetonUser.Visible = true;
                this.nomUser.Visible = true;
                this.connexionBtn.Hide();
                this.logOutbtn.Visible = true;
            }
        }

        private void exitToolStripMenuItem_Click(object sender, EventArgs e)
        {
            thread.Abort();
            Environment.Exit(0);
        }

        private void homeBtn_Click(object sender, EventArgs e)
        {
            //MessageBox.Show("Home cliquer");
        }

        private void profilBtn_Click(object sender, EventArgs e)
        {
            profil = new Profil(this.user,this.allPari);
            profil.Show();
        }

        private void demandeJetonBtn_Click(object sender, EventArgs e)
        {
            demandeJeton = new DemandeJeton(this.user,this.allPari);
            demandeJeton.Show();
        }

        private void connexionBtn_Click(object sender, EventArgs e)
        {
            Button btn = (Button)sender;
            login = new Login();
            login.Show();
            Hide();
        }

        // la fonction qui génère des nouveaux boutons
        private void generateButtons(List<Model.Match> allmatch)
        {
            int hauteur = 32,
                largeur = 64,
                left = 150,
                margin = 20;
            for (int i = 0; i < allmatch.Count; i++)
            {
                Label match = new Label();
                match.Name = "match" + i.ToString();
                match.Text = allMatch[i].equipe1.nom + "/" + allMatch[i].equipe2.nom;
                match.Top = i * hauteur + margin + 10;
                match.Left = 50;
                this.containerPari.Controls.Add(match);
                Button cotev1 = new Button();
                cotev1.Width = largeur;
                cotev1.Height = hauteur;
                cotev1.Name = "cotev1" + i.ToString();
                cotev1.Text = allMatch[i].cotev1;
                cotev1.Tag = allMatch[i];
                cotev1.BackColor = Color.Green;
                cotev1.FlatStyle = FlatStyle.Flat;
                // ajouter un listener
                cotev1.Click += cotev1_Click;
                cotev1.Top = i * hauteur + margin;
                cotev1.Left = left;
                this.containerPari.Controls.Add(cotev1);

                Button cotex = new Button();
                cotex.Width = largeur;
                cotex.Height = hauteur;
                cotex.Name = "cotex" + i.ToString();
                cotex.Text = allMatch[i].cotex;
                cotex.Tag = allMatch[i];
                cotex.BackColor = Color.Firebrick;
                cotex.FlatStyle = FlatStyle.Flat;
                // ajouter un listener
                cotex.Click += cotex_Click;
                cotex.Top = i * hauteur + margin;
                cotex.Left = largeur + left + 10;
                this.containerPari.Controls.Add(cotex);

                Button cotev2 = new Button();
                cotev2.Width = largeur;
                cotev2.Height = hauteur;
                cotev2.BackColor = Color.Green;
                cotev2.FlatStyle = FlatStyle.Flat;
                cotev2.Name = "cotev2" + i.ToString();
                cotev2.Text = allMatch[i].cotev2;
                cotev2.Tag = allMatch[i];
                // ajouter un listener
                cotev2.Click += cotev2_Click;
                cotev2.Top = i * hauteur + margin;
                cotev2.Left = 2 * largeur + left + 20;
                this.containerPari.Controls.Add(cotev2);
                margin += 5;
            }

        }
        private void createPari(List<Model.Pari> allPari)
        {
            int nomPariTop = 5;
            for (int i = 0; i < allPari.Count; i++)
            {
                Panel pannelPari = new Panel();
                Label nomPari = new Label();
                Label info = new Label();
                Label labelCote = new Label();
                Label labelMise = new Label();
                Label labelGain = new Label();
                Button btnCalculGain = new Button();
                Label cote = new Label();
                Label gain = new Label();

                this.miseJeton = new TextBox();
                miseJeton.Name = "miseJeton" + i;
                miseJeton.Text = "" + allPari[i].mise;
                miseJeton.Top = 5;
                miseJeton.Left = 156;
                miseJeton.Width = 105;

                btnCalculGain.Top = 5;
                btnCalculGain.Left = 264;
                btnCalculGain.Width = 110;
                btnCalculGain.Height = 23;
                btnCalculGain.Name = "CalculGain" + i;
                btnCalculGain.Text = "Miser";
                btnCalculGain.BackColor = Color.Firebrick;
                btnCalculGain.FlatStyle = FlatStyle.Flat;
                TagMise tag = new TagMise(allPari[i], i);
                btnCalculGain.Tag = tag;
                //Debug.WriteLine("SORTI  "+ miseJeton.Text);
                btnCalculGain.Click += btnCalculGain_Click;

                cote.Text = "" + allPari[i].coteJouer;
                cote.Width = 80;
                cote.Top = 30;
                cote.Left = 156;
                labelCote.Text = "Cote:";
                labelCote.Width = 35;
                labelCote.Top = 30;
                labelCote.Left = 120;
                gain.Text = "" + allPari[i].gainObtenu;
                gain.Width = 80;
                gain.Top = 30;
                gain.Left = 300;
                labelGain.Text = "Gain:";
                labelGain.Width = 35;
                labelGain.Top = 30;
                labelGain.Left = 264;
                info.Text = "Parametrer votre pari:";
                info.Top = 5;
                info.Left = 5;
                info.Width = 110;
                labelMise.Text = "Mise:";
                labelMise.Width = 35;
                labelMise.Top = 5;
                labelMise.Left = 120;
                nomPari.Name = "nomPari" + i;
                nomPari.Text = allPari[i].detailsparis[0].match.equipe1.nom + "/" + allPari[i].detailsparis[0].match.equipe2.nom;
                //nomPari.Text = btn.Tag as String;
                nomPari.Top = 30;
                nomPari.Left = 5;
                nomPari.Width = 110;
                pannelPari.Top = nomPariTop;
                pannelPari.Left = 5;
                pannelPari.Width = 385;
                pannelPari.Height = 60;
                pannelPari.BackColor = Color.White;
                pannelPari.Controls.Add(gain);
                pannelPari.Controls.Add(cote);
                pannelPari.Controls.Add(labelGain);
                pannelPari.Controls.Add(labelCote);
                pannelPari.Controls.Add(info);
                pannelPari.Controls.Add(labelMise);
                pannelPari.Controls.Add(miseJeton);
                pannelPari.Controls.Add(nomPari);
                pannelPari.Controls.Add(btnCalculGain);
                this.panelDopari.Controls.Add(pannelPari);
                nomPariTop += 65;
            }
        }
        // LISTENER
        private void btnCalculGain_Click(object sender, EventArgs e)
        {
            Button btn = (Button)sender;
            TagMise tag = btn.Tag as TagMise;
            float newMise = float.Parse(this.miseJeton.Text);
            float cote = this.allPari[tag.indice].coteJouer;
            float newGain = newMise * cote;
            this.allPari[tag.indice].mise = newMise;
            this.allPari[tag.indice].gainObtenu = newGain;
            Console.WriteLine("tag " + this.miseJeton.Name);
            this.Close();
            Home h = new Home(this.user, allPari);
            h.Show();
        }
        private void cotev1_Click(object sender, EventArgs e)
        {
            Button btn = (Button)sender;
            Match m = btn.Tag as Match;
            Boolean exist = this.pariExist(m);
            if (!exist)
            {
                Hide();
                float coteJouer = float.Parse(btn.Text);
                this.miser = new Miser(user, this.allPari, m, "V1", coteJouer);
                miser.Show();
            }
            else
            {
                this.Close();
                Home h = new Home(this.user, allPari);
                h.Show();
            }
        }
        private void cotex_Click(object sender, EventArgs e)
        {
            Button btn = (Button)sender;
            Match m = btn.Tag as Match;
            Boolean exist = this.pariExist(m);
            if (!exist)
            {
                Hide();
                float coteJouer = float.Parse(btn.Text);
                this.miser = new Miser(user, this.allPari, m, "X", coteJouer);
                miser.Show();
            }
            else
            {
                this.Close();
                Home h = new Home(this.user, allPari);
                h.Show();
            }
        }
        private void cotev2_Click(object sender, EventArgs e)
        {
            Button btn = (Button)sender;
            Match m = btn.Tag as Match;
            Boolean exist = this.pariExist(m);
            if (!exist)
            {
                Hide();
                float coteJouer = float.Parse(btn.Text);
                this.miser = new Miser(user, this.allPari, m, "V2", coteJouer);
                miser.Show();
            }
            else
            {
                this.Close();
                Home h = new Home(this.user, allPari);
                h.Show();
            }
        }

        private async void placerPariBtn_Click(object sender, EventArgs e)
        {
            this.panelChargement.Visible = true;
            Button btn = sender as Button;
            btn.Text = "En attente";
            btn.Enabled = false;
            UseWaitCursor = true;
            String res="";
            if (this.allPari.Count < 1 || totalMise>this.user.jetons)
            {
                this.panelChargement.Visible = false;
                btn.Text = "Placer vos paris";
                btn.Enabled = true;
                UseWaitCursor = false;
                MessageBox.Show("Veuillez placer votre pari! ou verifier que vos jetons sont suffisants");
            }
            else
            {
                //Parie non grouper
                if (!this.gouperPari.Checked)
                {
                    for (int i = 0; i < this.allPari.Count; i++)
                    {
                        res = await serviceGrails.PlacerPari(allPari[i]);
                    }
                }
                // Parie GROUPER 
                else
                {
                    Pari newPari = new Pari();
                    newPari.idclient = this.user._id;
                    newPari.mise = this.totalMise;
                    DetailPari[] listDetail = new DetailPari[allPari.Count];
                    for (int i = 0; i < allPari.Count; i++)
                    {
                        Match m = new Match(allPari[i].detailsparis[0].match.id);
                        DetailPari dp = new DetailPari("" + i + 1, m, allPari[i].detailsparis[0].prono);
                        listDetail[i] = dp;
                    }
                    newPari.detailsparis = listDetail;
                    res = await serviceGrails.PlacerPari(newPari);
                    //afficher json anle pari
                    //Console.WriteLine(JsonConvert.SerializeObject(newPari,Newtonsoft.Json.Formatting.None,new JsonSerializerSettings{NullValueHandling = NullValueHandling.Ignore}));
                }
            }
            // utilisateur non connecté
            if (res == "900")
            {
                btn.Text = "Placer vos paris";
                btn.Enabled = true;
                UseWaitCursor = false;
                login = new Login();
                login.Show();
                Hide();
            }
            else if (res == "200")
            {
                this.panelChargement.Visible = false;
                btn.Text = "Placer vos paris";
                btn.Enabled = true;
                UseWaitCursor = false;
                MessageBox.Show("Vos paris ont bien été placer!");
                this.Close();
                this.allPari = new List<Model.Pari>();
                Home h = new Home(this.user, allPari);
                h.Show();
            }
        }

        private Boolean pariExist(Match match)
        {
            Boolean res = false;
            if (this.allPari.Count > 0)
            {
                for (int i = 0; i < this.allPari.Count; i++)
                {
                    //Console.WriteLine("ALLPari:  " + this.allPari[i].detailsparis[0].match.id + " MATCH: " + match.id);
                    if (this.allPari[i].detailsparis[0].match.id == match.id)
                    {
                        res = true;
                        this.allPari.RemoveAt(i);
                        //Console.WriteLine("TAILLE VAOVAO ALL PARI: "+ this.allPari.Count);
                        break;
                    }
                }
            }
            return res;
        }

        private void logOutbtn_Click(object sender, EventArgs e)
        {
            DialogResult dialogResult = MessageBox.Show("Etes vous sur de vouloir vous deconnecter?", "Déconnection", MessageBoxButtons.YesNo);
            if (dialogResult == DialogResult.Yes)
            {
                Dispose();
                this.user = new Model.Login();
                this.allPari = new List<Model.Pari>();
                Home h = new Home(user, allPari);
                h.Show();
            }
            else if (dialogResult == DialogResult.No)
            {
                this.Close();
                Home h = new Home(this.user, allPari);
                h.Show();
            }
        }

        private void gouperPari_CheckedChanged(object sender, EventArgs e)
        {
            CheckBox check = (CheckBox)sender;
            if (check.Checked)
            {
                this.labelMiseTotal.Visible = true;
                this.miseTotal.Text = "" + totalMise + " jetons";
                this.miseTotal.Visible = true;
            }
            else
            {
                this.labelMiseTotal.Visible = false;
                this.miseTotal.Visible = false;
            }
        }

        private void historiqueBtn_Click(object sender, EventArgs e)
        {
            Hide();
            Historique hist = new Historique(this.user,this.allPari);
            hist.Show();
        }
    }
}
