using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using System.Threading.Tasks;
using winform_TpTransversal.Model;

namespace winform_TpTransversal.Service
{
    public class ServiceGrails
    {
        string uri = "https://tpt-server-grails.herokuapp.com";

        public async Task<(List<Match> listeMatch, String statusCode)> GetAllMatch()
        {
            List<Model.Match> listeMatch = new List<Model.Match>();
            String statusCode = "";
            try
            {
                using (var client = new HttpClient())
                {
                    client.BaseAddress = new Uri(uri + "/matchApi/");
                    client.DefaultRequestHeaders.Accept.Clear();
                    client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
                    HttpResponseMessage Res = await client.GetAsync("matchs");
                    if (Res.IsSuccessStatusCode)
                    {
                        var result = Res.Content.ReadAsStringAsync().Result;
                        var jsonData = JArray.Parse(result);
                        foreach (JObject data in jsonData)
                        {
                            Model.Match match = new Model.Match();
                            int id = (data.GetValue("id") != null) ? match.id = (int)data.GetValue("id") : match.id = 0;
                            String cotev1 = (data.GetValue("cotev1") != null) ? match.cotev1 = data.GetValue("cotev1").ToString() : match.cotev1 = "";
                            String cotex = (data.GetValue("cotex") != null) ? match.cotex = data.GetValue("cotex").ToString() : match.cotex = "";
                            String resultat = (data.GetValue("resultat") != null) ? match.resultat = data.GetValue("resultat").ToString() : match.resultat = "";
                            String lieumatch = (data.GetValue("lieumatch") != null) ? match.lieumatch = data.GetValue("lieumatch").ToString() : match.lieumatch = "";
                            String datematch = (data.GetValue("datematch") != null) ? match.datematch = data.GetValue("datematch").ToString() : match.datematch = "";
                            String cotev2 = (data.GetValue("cotev2") != null) ? match.cotev2 = data.GetValue("cotev2").ToString() : match.cotev2 = "";
                            Model.Equipe dataEquipe1 = new Model.Equipe();
                            if (data.GetValue("equipe1") != null && !String.IsNullOrEmpty(data.GetValue("equipe1").ToString()))
                            {
                                JObject equipe = JObject.Parse(data.GetValue("equipe1").ToString());
                                int idEquipe1 = (equipe.GetValue("id") != null) ? dataEquipe1.id = (int)equipe.GetValue("id") : dataEquipe1.id = 0;
                                String note = (equipe.GetValue("note") != null) ? dataEquipe1.note = equipe.GetValue("note").ToString() : dataEquipe1.note = "";
                                String nom = (equipe.GetValue("nom") != null) ? dataEquipe1.nom = equipe.GetValue("nom").ToString() : dataEquipe1.nom = "";
                            }
                            Model.Equipe dataEquipe2 = new Model.Equipe();
                            if (data.GetValue("equipe2") != null && !String.IsNullOrEmpty(data.GetValue("equipe2").ToString()))
                            {
                                JObject equipe = JObject.Parse(data.GetValue("equipe2").ToString());
                                int idEquipe2 = (equipe.GetValue("id") != null) ? dataEquipe2.id = (int)equipe.GetValue("id") : dataEquipe2.id = 0;
                                String note = (equipe.GetValue("note") != null) ? dataEquipe2.note = equipe.GetValue("note").ToString() : dataEquipe2.note = "";
                                String nom = (equipe.GetValue("nom") != null) ? dataEquipe2.nom = equipe.GetValue("nom").ToString() : dataEquipe2.nom = "";
                            }
                            match.equipe1 = dataEquipe1;
                            match.equipe2 = dataEquipe2;
                            String fini = (data.GetValue("fini") != null) ? match.fini = data.GetValue("fini").ToString() : match.fini = "";

                            listeMatch.Add(match);
                        }
                        statusCode = "200";
                    }
                    else
                    {
                        statusCode = Res.StatusCode.ToString();
                    }
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
                statusCode = "900";
            }
            return (listeMatch, statusCode);
        }

        public async Task<string> PlacerPari(Pari pari)
        {
            if (pari.idclient == null) { return "900"; }
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(uri);
                client.DefaultRequestHeaders.Accept.Clear();
                client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
                var httpContent = new StringContent(JsonConvert.SerializeObject(pari,
                            Newtonsoft.Json.Formatting.None,
                            new JsonSerializerSettings
                            {
                                NullValueHandling = NullValueHandling.Ignore
                            }), Encoding.UTF8, "application/json");
                HttpResponseMessage Res = await client.PostAsync("/parisApi/paris/", httpContent);
                if (Res.IsSuccessStatusCode)
                {
                    return "200";
                }
                else
                {
                    //Debug.WriteLine("PARI NON PLACER -------   " + Res.ToString());
                    return Res.StatusCode.ToString();
                }
            }
        }

        public async Task<(List<HistoriquePari> historique, string statusCode)> GetHistorique(String idClient)
        {
            List<HistoriquePari> listehist = new List<HistoriquePari>();
            string statusCode = "";
            try
            {
                using (var client = new HttpClient())
                {
                    client.BaseAddress = new Uri(uri);
                    client.DefaultRequestHeaders.Accept.Clear();
                    client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
                    HttpResponseMessage Res = await client.GetAsync("/parisApi/listeParis?idclient=" + idClient);
                    if (Res.IsSuccessStatusCode)
                    {
                        var result = Res.Content.ReadAsStringAsync().Result;
                        var jsonData = JArray.Parse(result);
                        foreach (JObject data in jsonData)
                        {
                            HistoriquePari hist = new HistoriquePari();
                            int id = (data.GetValue("id") != null) ? hist.id = (int)data.GetValue("id") : hist.id = 0;
                            int nbperdu = (data.GetValue("nbperdu") != null) ? hist.nbperdu = (int)data.GetValue("nbperdu") : hist.nbperdu = 0;
                            string nbmatch = (data.GetValue("nbmatch") != null) ? hist.nbmatch = data.GetValue("nbmatch").ToString() : hist.nbmatch = "";
                            string vcode = (data.GetValue("vcode") != null) ? hist.vcode = data.GetValue("vcode").ToString() : hist.vcode = "";
                            string dateparis = (data.GetValue("dateparis") != null) ? hist.dateparis = data.GetValue("dateparis").ToString() : hist.dateparis = "";
                            float coteglobal = (data.GetValue("coteglobal") != null) ? hist.coteglobal = (float)data.GetValue("coteglobal") : hist.coteglobal = 0;
                            string idclient = (data.GetValue("idclient") != null) ? hist.idclient = data.GetValue("idclient").ToString() : hist.idclient = "";
                            float gainpossible = (data.GetValue("gainpossible") != null) ? hist.gainpossible = (float)data.GetValue("gainpossible") : hist.gainpossible = 0;
                            string gain = (data.GetValue("gain") != null) ? hist.gain = data.GetValue("gain").ToString() : hist.gain = "";
                            float nbgain = (data.GetValue("nbgain") != null) ? hist.nbgain = (float)data.GetValue("nbgain") : hist.nbgain = 0;
                            float mise = (data.GetValue("mise") != null) ? hist.mise = (float)data.GetValue("mise") : hist.mise = 0;
                            listehist.Add(hist);
                        }
                        statusCode = "200";
                    }
                    else
                    {
                        statusCode = Res.StatusCode.ToString();
                    }
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
                statusCode = "900";
            }
            Console.WriteLine(JsonConvert.SerializeObject(listehist));
            return (listehist, statusCode);
        }

        public async Task<HistoriquePari> GetHistoriqueById(string idHistorique)
        {
            HistoriquePari hist = null;
            try
            {
                using (var client = new HttpClient())
                {
                    client.BaseAddress = new Uri(uri);
                    HttpResponseMessage response = await client.GetAsync("/parisApi/paris?id=" + idHistorique);
                    if (response.IsSuccessStatusCode)
                    {
                        hist = await response.Content.ReadAsAsync<HistoriquePari>();
                    }
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
            }
            //Console.WriteLine("JSON SERIALIZE : " + JsonConvert.SerializeObject(hist));
            return hist;
        }

    }
}
