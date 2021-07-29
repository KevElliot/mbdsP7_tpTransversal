
namespace winform_TpTransversal.Vu
{
    partial class Home
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.panel1 = new System.Windows.Forms.Panel();
            this.messageLabel = new System.Windows.Forms.Label();
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.homeToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.exitToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.label1 = new System.Windows.Forms.Label();
            this.nomUser = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.jetonUser = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.containerPari = new System.Windows.Forms.Panel();
            this.label7 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.panelDopari = new System.Windows.Forms.Panel();
            this.miseTotal = new System.Windows.Forms.Label();
            this.labelMiseTotal = new System.Windows.Forms.Label();
            this.placerPariBtn = new System.Windows.Forms.Button();
            this.gouperPari = new System.Windows.Forms.CheckBox();
            this.panelChargement = new System.Windows.Forms.Panel();
            this.label8 = new System.Windows.Forms.Label();
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            this.logOutbtn = new System.Windows.Forms.Button();
            this.connexionBtn = new System.Windows.Forms.Button();
            this.historiqueBtn = new System.Windows.Forms.Button();
            this.demandeJetonBtn = new System.Windows.Forms.Button();
            this.profilBtn = new System.Windows.Forms.Button();
            this.homeBtn = new System.Windows.Forms.Button();
            this.pictureBox2 = new System.Windows.Forms.PictureBox();
            this.panel1.SuspendLayout();
            this.menuStrip1.SuspendLayout();
            this.containerPari.SuspendLayout();
            this.panelDopari.SuspendLayout();
            this.panelChargement.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox2)).BeginInit();
            this.SuspendLayout();
            // 
            // panel1
            // 
            this.panel1.BackColor = System.Drawing.Color.Firebrick;
            this.panel1.Controls.Add(this.messageLabel);
            this.panel1.Controls.Add(this.logOutbtn);
            this.panel1.Controls.Add(this.connexionBtn);
            this.panel1.Controls.Add(this.historiqueBtn);
            this.panel1.Controls.Add(this.demandeJetonBtn);
            this.panel1.Controls.Add(this.profilBtn);
            this.panel1.Controls.Add(this.homeBtn);
            this.panel1.Controls.Add(this.menuStrip1);
            this.panel1.Dock = System.Windows.Forms.DockStyle.Top;
            this.panel1.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(192)))), ((int)(((byte)(0)))), ((int)(((byte)(0)))));
            this.panel1.Location = new System.Drawing.Point(0, 0);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(931, 86);
            this.panel1.TabIndex = 0;
            // 
            // messageLabel
            // 
            this.messageLabel.AutoSize = true;
            this.messageLabel.Font = new System.Drawing.Font("Segoe UI", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.messageLabel.ForeColor = System.Drawing.Color.White;
            this.messageLabel.Location = new System.Drawing.Point(709, 47);
            this.messageLabel.Name = "messageLabel";
            this.messageLabel.Size = new System.Drawing.Size(142, 17);
            this.messageLabel.TabIndex = 12;
            this.messageLabel.Text = "Veuillez vous connecté!";
            this.messageLabel.Visible = false;
            // 
            // menuStrip1
            // 
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.homeToolStripMenuItem,
            this.exitToolStripMenuItem});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Size = new System.Drawing.Size(931, 24);
            this.menuStrip1.TabIndex = 5;
            this.menuStrip1.Text = "menuStrip1";
            // 
            // homeToolStripMenuItem
            // 
            this.homeToolStripMenuItem.Name = "homeToolStripMenuItem";
            this.homeToolStripMenuItem.Size = new System.Drawing.Size(52, 20);
            this.homeToolStripMenuItem.Text = "Home";
            // 
            // exitToolStripMenuItem
            // 
            this.exitToolStripMenuItem.Name = "exitToolStripMenuItem";
            this.exitToolStripMenuItem.Size = new System.Drawing.Size(38, 20);
            this.exitToolStripMenuItem.Text = "Exit";
            this.exitToolStripMenuItem.Click += new System.EventHandler(this.exitToolStripMenuItem_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.BackColor = System.Drawing.Color.Transparent;
            this.label1.Font = new System.Drawing.Font("Segoe UI", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(627, 89);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(56, 17);
            this.label1.TabIndex = 2;
            this.label1.Text = "Bonjour,";
            this.label1.Visible = false;
            // 
            // nomUser
            // 
            this.nomUser.AutoSize = true;
            this.nomUser.Font = new System.Drawing.Font("Segoe UI Semibold", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.nomUser.Location = new System.Drawing.Point(683, 89);
            this.nomUser.Name = "nomUser";
            this.nomUser.Size = new System.Drawing.Size(36, 17);
            this.nomUser.TabIndex = 3;
            this.nomUser.Text = "nom";
            this.nomUser.Visible = false;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Segoe UI", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label2.Location = new System.Drawing.Point(764, 89);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(72, 17);
            this.label2.TabIndex = 4;
            this.label2.Text = "Vous aviez:";
            this.label2.Visible = false;
            // 
            // jetonUser
            // 
            this.jetonUser.AutoSize = true;
            this.jetonUser.Font = new System.Drawing.Font("Segoe UI Semibold", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.jetonUser.Location = new System.Drawing.Point(836, 89);
            this.jetonUser.Name = "jetonUser";
            this.jetonUser.Size = new System.Drawing.Size(45, 17);
            this.jetonUser.TabIndex = 5;
            this.jetonUser.Text = "jetons";
            this.jetonUser.Visible = false;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.BackColor = System.Drawing.Color.Transparent;
            this.label4.Font = new System.Drawing.Font("Segoe UI", 14.25F, ((System.Drawing.FontStyle)((System.Drawing.FontStyle.Bold | System.Drawing.FontStyle.Underline))), System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label4.Location = new System.Drawing.Point(12, 120);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(201, 25);
            this.label4.TabIndex = 7;
            this.label4.Text = "Les paris disponible : ";
            // 
            // containerPari
            // 
            this.containerPari.AutoScroll = true;
            this.containerPari.BackColor = System.Drawing.Color.Gainsboro;
            this.containerPari.Controls.Add(this.label7);
            this.containerPari.Controls.Add(this.label6);
            this.containerPari.Controls.Add(this.label5);
            this.containerPari.Controls.Add(this.label3);
            this.containerPari.Location = new System.Drawing.Point(12, 159);
            this.containerPari.Name = "containerPari";
            this.containerPari.Size = new System.Drawing.Size(388, 465);
            this.containerPari.TabIndex = 8;
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.BackColor = System.Drawing.Color.Transparent;
            this.label7.Font = new System.Drawing.Font("Segoe UI", 9.75F, ((System.Drawing.FontStyle)((System.Drawing.FontStyle.Bold | System.Drawing.FontStyle.Underline))), System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label7.Location = new System.Drawing.Point(319, 1);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(24, 17);
            this.label7.TabIndex = 15;
            this.label7.Text = "V2";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.BackColor = System.Drawing.Color.Transparent;
            this.label6.Font = new System.Drawing.Font("Segoe UI", 9.75F, ((System.Drawing.FontStyle)((System.Drawing.FontStyle.Bold | System.Drawing.FontStyle.Underline))), System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label6.Location = new System.Drawing.Point(246, 1);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(17, 17);
            this.label6.TabIndex = 14;
            this.label6.Text = "X";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.BackColor = System.Drawing.Color.Transparent;
            this.label5.Font = new System.Drawing.Font("Segoe UI", 9.75F, ((System.Drawing.FontStyle)((System.Drawing.FontStyle.Bold | System.Drawing.FontStyle.Underline))), System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label5.Location = new System.Drawing.Point(172, 1);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(24, 17);
            this.label5.TabIndex = 13;
            this.label5.Text = "V1";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.BackColor = System.Drawing.Color.Transparent;
            this.label3.Font = new System.Drawing.Font("Segoe UI", 9.75F, ((System.Drawing.FontStyle)((System.Drawing.FontStyle.Bold | System.Drawing.FontStyle.Underline))), System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label3.Location = new System.Drawing.Point(50, 1);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(52, 17);
            this.label3.TabIndex = 12;
            this.label3.Text = "Matchs";
            // 
            // panelDopari
            // 
            this.panelDopari.AutoScroll = true;
            this.panelDopari.BackColor = System.Drawing.Color.Gainsboro;
            this.panelDopari.Controls.Add(this.miseTotal);
            this.panelDopari.Controls.Add(this.labelMiseTotal);
            this.panelDopari.Location = new System.Drawing.Point(536, 159);
            this.panelDopari.Name = "panelDopari";
            this.panelDopari.Size = new System.Drawing.Size(395, 427);
            this.panelDopari.TabIndex = 9;
            // 
            // miseTotal
            // 
            this.miseTotal.AccessibleName = "miseTotal";
            this.miseTotal.AutoSize = true;
            this.miseTotal.Font = new System.Drawing.Font("Segoe UI Semibold", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.miseTotal.Location = new System.Drawing.Point(69, 410);
            this.miseTotal.Name = "miseTotal";
            this.miseTotal.Size = new System.Drawing.Size(65, 17);
            this.miseTotal.TabIndex = 12;
            this.miseTotal.Text = "miseTotal";
            this.miseTotal.Visible = false;
            // 
            // labelMiseTotal
            // 
            this.labelMiseTotal.AccessibleName = "labelMiseTotal";
            this.labelMiseTotal.AutoSize = true;
            this.labelMiseTotal.Font = new System.Drawing.Font("Segoe UI", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelMiseTotal.Location = new System.Drawing.Point(3, 410);
            this.labelMiseTotal.Name = "labelMiseTotal";
            this.labelMiseTotal.Size = new System.Drawing.Size(70, 17);
            this.labelMiseTotal.TabIndex = 12;
            this.labelMiseTotal.Text = "Total mise:";
            this.labelMiseTotal.Visible = false;
            // 
            // placerPariBtn
            // 
            this.placerPariBtn.BackColor = System.Drawing.Color.Firebrick;
            this.placerPariBtn.Cursor = System.Windows.Forms.Cursors.Hand;
            this.placerPariBtn.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.placerPariBtn.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.placerPariBtn.ForeColor = System.Drawing.Color.Silver;
            this.placerPariBtn.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.placerPariBtn.Location = new System.Drawing.Point(536, 585);
            this.placerPariBtn.Name = "placerPariBtn";
            this.placerPariBtn.Size = new System.Drawing.Size(395, 51);
            this.placerPariBtn.TabIndex = 6;
            this.placerPariBtn.Text = "Placer vos paris";
            this.placerPariBtn.UseVisualStyleBackColor = false;
            this.placerPariBtn.Click += new System.EventHandler(this.placerPariBtn_Click);
            // 
            // gouperPari
            // 
            this.gouperPari.AutoSize = true;
            this.gouperPari.Font = new System.Drawing.Font("Segoe UI Semibold", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.gouperPari.Location = new System.Drawing.Point(806, 136);
            this.gouperPari.Name = "gouperPari";
            this.gouperPari.Size = new System.Drawing.Size(113, 17);
            this.gouperPari.TabIndex = 11;
            this.gouperPari.Text = "Grouper les paris";
            this.gouperPari.UseVisualStyleBackColor = true;
            this.gouperPari.CheckedChanged += new System.EventHandler(this.gouperPari_CheckedChanged);
            // 
            // panelChargement
            // 
            this.panelChargement.BackColor = System.Drawing.Color.DimGray;
            this.panelChargement.Controls.Add(this.pictureBox2);
            this.panelChargement.Controls.Add(this.label8);
            this.panelChargement.Cursor = System.Windows.Forms.Cursors.Default;
            this.panelChargement.Location = new System.Drawing.Point(249, 232);
            this.panelChargement.Name = "panelChargement";
            this.panelChargement.Size = new System.Drawing.Size(455, 235);
            this.panelChargement.TabIndex = 12;
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.BackColor = System.Drawing.Color.Transparent;
            this.label8.Font = new System.Drawing.Font("Segoe UI Semibold", 14.25F, ((System.Drawing.FontStyle)((System.Drawing.FontStyle.Bold | System.Drawing.FontStyle.Italic))), System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label8.Location = new System.Drawing.Point(131, 38);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(216, 25);
            this.label8.TabIndex = 13;
            this.label8.Text = "Chargement en cours... ";
            // 
            // pictureBox1
            // 
            this.pictureBox1.BackColor = System.Drawing.Color.Transparent;
            this.pictureBox1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.pictureBox1.Image = global::winform_TpTransversal.Properties.Resources.fondHome;
            this.pictureBox1.Location = new System.Drawing.Point(0, 86);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(931, 550);
            this.pictureBox1.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox1.TabIndex = 1;
            this.pictureBox1.TabStop = false;
            // 
            // logOutbtn
            // 
            this.logOutbtn.BackColor = System.Drawing.Color.Black;
            this.logOutbtn.Cursor = System.Windows.Forms.Cursors.Hand;
            this.logOutbtn.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.logOutbtn.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.logOutbtn.ForeColor = System.Drawing.Color.White;
            this.logOutbtn.Image = global::winform_TpTransversal.Properties.Resources.Logout;
            this.logOutbtn.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.logOutbtn.Location = new System.Drawing.Point(857, 29);
            this.logOutbtn.Name = "logOutbtn";
            this.logOutbtn.Size = new System.Drawing.Size(62, 51);
            this.logOutbtn.TabIndex = 13;
            this.logOutbtn.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.logOutbtn.UseVisualStyleBackColor = false;
            this.logOutbtn.Visible = false;
            this.logOutbtn.Click += new System.EventHandler(this.logOutbtn_Click);
            // 
            // connexionBtn
            // 
            this.connexionBtn.BackColor = System.Drawing.Color.Firebrick;
            this.connexionBtn.Cursor = System.Windows.Forms.Cursors.Hand;
            this.connexionBtn.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.connexionBtn.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.connexionBtn.ForeColor = System.Drawing.Color.White;
            this.connexionBtn.Image = global::winform_TpTransversal.Properties.Resources.Login;
            this.connexionBtn.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.connexionBtn.Location = new System.Drawing.Point(857, 29);
            this.connexionBtn.Name = "connexionBtn";
            this.connexionBtn.Size = new System.Drawing.Size(62, 51);
            this.connexionBtn.TabIndex = 4;
            this.connexionBtn.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.connexionBtn.UseVisualStyleBackColor = false;
            this.connexionBtn.Click += new System.EventHandler(this.connexionBtn_Click);
            // 
            // historiqueBtn
            // 
            this.historiqueBtn.Cursor = System.Windows.Forms.Cursors.Hand;
            this.historiqueBtn.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.historiqueBtn.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.historiqueBtn.ForeColor = System.Drawing.Color.White;
            this.historiqueBtn.Image = global::winform_TpTransversal.Properties.Resources.hist;
            this.historiqueBtn.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.historiqueBtn.Location = new System.Drawing.Point(580, 29);
            this.historiqueBtn.Name = "historiqueBtn";
            this.historiqueBtn.Size = new System.Drawing.Size(162, 51);
            this.historiqueBtn.TabIndex = 3;
            this.historiqueBtn.Text = "Historique";
            this.historiqueBtn.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.historiqueBtn.UseVisualStyleBackColor = true;
            this.historiqueBtn.Click += new System.EventHandler(this.historiqueBtn_Click);
            // 
            // demandeJetonBtn
            // 
            this.demandeJetonBtn.Cursor = System.Windows.Forms.Cursors.Hand;
            this.demandeJetonBtn.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.demandeJetonBtn.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.demandeJetonBtn.ForeColor = System.Drawing.Color.White;
            this.demandeJetonBtn.Image = global::winform_TpTransversal.Properties.Resources.jeton;
            this.demandeJetonBtn.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.demandeJetonBtn.Location = new System.Drawing.Point(374, 29);
            this.demandeJetonBtn.Name = "demandeJetonBtn";
            this.demandeJetonBtn.Size = new System.Drawing.Size(194, 51);
            this.demandeJetonBtn.TabIndex = 2;
            this.demandeJetonBtn.Text = "Demande jeton";
            this.demandeJetonBtn.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.demandeJetonBtn.UseVisualStyleBackColor = true;
            this.demandeJetonBtn.Click += new System.EventHandler(this.demandeJetonBtn_Click);
            // 
            // profilBtn
            // 
            this.profilBtn.Cursor = System.Windows.Forms.Cursors.Hand;
            this.profilBtn.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.profilBtn.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.profilBtn.ForeColor = System.Drawing.Color.White;
            this.profilBtn.Image = global::winform_TpTransversal.Properties.Resources.profil;
            this.profilBtn.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.profilBtn.Location = new System.Drawing.Point(248, 29);
            this.profilBtn.Name = "profilBtn";
            this.profilBtn.Size = new System.Drawing.Size(114, 51);
            this.profilBtn.TabIndex = 1;
            this.profilBtn.Text = "Profil";
            this.profilBtn.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.profilBtn.UseVisualStyleBackColor = true;
            this.profilBtn.Click += new System.EventHandler(this.profilBtn_Click);
            // 
            // homeBtn
            // 
            this.homeBtn.Cursor = System.Windows.Forms.Cursors.Hand;
            this.homeBtn.Enabled = false;
            this.homeBtn.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.homeBtn.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.homeBtn.ForeColor = System.Drawing.Color.White;
            this.homeBtn.Image = global::winform_TpTransversal.Properties.Resources.home;
            this.homeBtn.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.homeBtn.Location = new System.Drawing.Point(12, 29);
            this.homeBtn.Name = "homeBtn";
            this.homeBtn.Size = new System.Drawing.Size(122, 51);
            this.homeBtn.TabIndex = 0;
            this.homeBtn.Text = "Home";
            this.homeBtn.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.homeBtn.UseVisualStyleBackColor = true;
            this.homeBtn.Click += new System.EventHandler(this.homeBtn_Click);
            // 
            // pictureBox2
            // 
            this.pictureBox2.Image = global::winform_TpTransversal.Properties.Resources.loading;
            this.pictureBox2.Location = new System.Drawing.Point(136, 83);
            this.pictureBox2.Name = "pictureBox2";
            this.pictureBox2.Size = new System.Drawing.Size(206, 115);
            this.pictureBox2.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.pictureBox2.TabIndex = 14;
            this.pictureBox2.TabStop = false;
            // 
            // Home
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.White;
            this.ClientSize = new System.Drawing.Size(931, 636);
            this.Controls.Add(this.panelChargement);
            this.Controls.Add(this.gouperPari);
            this.Controls.Add(this.placerPariBtn);
            this.Controls.Add(this.panelDopari);
            this.Controls.Add(this.containerPari);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.jetonUser);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.nomUser);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.pictureBox1);
            this.Controls.Add(this.panel1);
            this.MaximizeBox = false;
            this.MaximumSize = new System.Drawing.Size(947, 675);
            this.MinimumSize = new System.Drawing.Size(947, 675);
            this.Name = "Home";
            this.ShowInTaskbar = false;
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "PariLocal - Home";
            this.Load += new System.EventHandler(this.Home_Load);
            this.panel1.ResumeLayout(false);
            this.panel1.PerformLayout();
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            this.containerPari.ResumeLayout(false);
            this.containerPari.PerformLayout();
            this.panelDopari.ResumeLayout(false);
            this.panelDopari.PerformLayout();
            this.panelChargement.ResumeLayout(false);
            this.panelChargement.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox2)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.Button homeBtn;
        private System.Windows.Forms.Button connexionBtn;
        private System.Windows.Forms.Button historiqueBtn;
        private System.Windows.Forms.Button demandeJetonBtn;
        private System.Windows.Forms.Button profilBtn;
        private System.Windows.Forms.PictureBox pictureBox1;
        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem homeToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem exitToolStripMenuItem;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label nomUser;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label jetonUser;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Panel containerPari;
        private System.Windows.Forms.Panel panelDopari;
        private System.Windows.Forms.Button placerPariBtn;
        private System.Windows.Forms.CheckBox gouperPari;
        private System.Windows.Forms.Label messageLabel;
        private System.Windows.Forms.Button logOutbtn;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label miseTotal;
        private System.Windows.Forms.Label labelMiseTotal;
        private System.Windows.Forms.Panel panelChargement;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.PictureBox pictureBox2;
    }
}