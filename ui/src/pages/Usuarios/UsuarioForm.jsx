import React from 'react';
import PropTypes from 'prop-types';
import {
  makeStyles,
  TextField,
  Button,
  CircularProgress
} from '@material-ui/core';
import { Save as SaveIcon } from '@material-ui/icons';

const useStyles = makeStyles((theme) => ({
  form: {
    textAlign: 'right'
  },
  textField: {
    marginBottom: theme.spacing(2)
  },
  wrapper: {
    margin: theme.spacing(1),
    position: 'relative',
    display: 'inline'
  },
  buttonProgress: {
    position: 'absolute',
    top: '50%',
    left: '50%',
    marginTop: -12,
    marginLeft: -12,
  },
}));

function UsuarioForm(props) {
  const classes = useStyles();

  return (
    <form autoComplete="off" className={classes.form} onSubmit={props.formSubmit}>
      {props.readOnly && 
        <TextField
          inputProps={{
            readOnly: true,
          }}
          label="Id"
          variant="outlined"
          size="small"
          className={classes.textField}
          fullWidth
          value={props.usuario.id}
          />
      }
      <TextField
        inputProps={{
          readOnly: props.readOnly,
        }}
        label="Nome"
        variant="outlined"
        size="small"
        className={classes.textField}
        required
        fullWidth
        value={props.usuario.nome}
        onChange={(e) => props.formValueChange('nome', e.target.value)}
        />
      {!props.readOnly &&
        <TextField
          inputProps={{
            readOnly: props.readOnly,
          }}
          label="E-mail"
          variant="outlined"
          size="small"
          className={classes.textField}
          margin="normal"
          required
          fullWidth
          value={props.usuario.email}
          onChange={(e) => props.formValueChange('email', e.target.value)}
          />
      }
      {!props.readOnly &&
        <div className={classes.wrapper}>
          <Button
              type="submit"
              variant="contained"
              color="secondary"
              size="medium"
              disabled={props.isSaving}
              startIcon={<SaveIcon />}>
              Salvar
            </Button>
            {props.isSaving && <CircularProgress size={24} className={classes.buttonProgress} />}
        </div>
      }
    </form>
  );
}

UsuarioForm.defaultProps = {
  isSaving: false,
  readOnly: false
};

UsuarioForm.propTypes = {
  usuario: PropTypes.object,
  isSaving: PropTypes.bool,
  readOnly: PropTypes.bool,
  formValueChange: PropTypes.func,
  formSubmit: PropTypes.func
};

export default React.memo(UsuarioForm);
