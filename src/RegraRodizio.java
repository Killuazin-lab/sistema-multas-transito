import java.util.Calendar;

public class RegraRodizio extends RegraMulta {
  private int finalPlaca;
  private String[] logradourosAfetados;
  private int diaDaSemana;
  private int tipoVeiculo;

  public RegraRodizio(/*int placa, String[] logra, int dia, int tipoVeic*/){
    /*this.finalPlaca = placa;
    this.logradourosAfetados = logra;
    this.diaDaSemana = dia;
    this.tipoVeiculo = tipoVeic;*/
  }

    /* Implementar métodos abstratos: 
  ->verificaNivelMulta()
  -> obterDescricaoMulta()
  (provenientes da classe RegraMulta)*/

  @Override
  public int verificaNivelMulta(Ocorrencia ocorrencia){String placa = ocorrencia.getPlaca();
    placa = ocorrencia.getPlaca();
    Calendar data = Calendar.getInstance();
    data.setTime(ocorrencia.getData());

    diaDaSemana = data.get(Calendar.DAY_OF_WEEK); // dias da semana, pelo que vi é: domingo = 1, segunda = 2.... sab = 7
    finalPlaca = Integer.parseInt(placa.substring(placa.length() - 1)); // aqui pega o ultimo digito da placa

    if (diaDaSemana == Calendar.SATURDAY || diaDaSemana == Calendar.SUNDAY) { //aqui verifica se é final de semana, se for, ignora o rodizio
        return NivelMulta.SEM_MULTA.getCodigo();
    }

    boolean emRodizio = false;

    switch (diaDaSemana) { //
        case Calendar.MONDAY:
            emRodizio = (finalPlaca == 1 || finalPlaca == 2);
            break;

        case Calendar.TUESDAY:
            emRodizio = (finalPlaca == 3 || finalPlaca == 4);
            break;

        case Calendar.WEDNESDAY:
            emRodizio = (finalPlaca == 5 || finalPlaca == 6);
            break;

        case Calendar.THURSDAY:
            emRodizio = (finalPlaca == 7 || finalPlaca == 8);
            break;

        case Calendar.FRIDAY:
            emRodizio = (finalPlaca == 9 || finalPlaca == 0);
            break;

        default:
            break;
    }

    if (emRodizio) { // aplica multa se o cara for pego no rodizio
        return NivelMulta.MULTA_GRAVE.getCodigo(); // rodizio é grave
    }

    return NivelMulta.SEM_MULTA.getCodigo(); // Fora do rodízio
  }
  @Override
  public String obterDescricaoMulta(Ocorrencia ocorrencia){
    return " Veículo circulando em dia e horário proibidos pelo rodízio em " +
                ocorrencia.getNomeLogradouro() + ". Último dígito da placa: " +
                ocorrencia.getPlaca().substring(ocorrencia.getPlaca().length() - 1) + ".";
  };

}
