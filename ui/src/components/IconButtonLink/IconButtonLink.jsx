import React, { useMemo, forwardRef } from 'react';
import PropTypes from 'prop-types';
import { IconButton } from '@material-ui/core';
import { Link } from 'react-router-dom';

function IconButtonLink(props) {
  const { icon, to } = props;

  const renderLink = useMemo(
    () => forwardRef((itemProps, ref) => <Link to={to} ref={ref} {...itemProps} />),
    [to],
  );

  return <IconButton component={renderLink}>{icon}</IconButton>;
}

IconButtonLink.propTypes = {
  icon: PropTypes.element.isRequired,
  to: PropTypes.string.isRequired,
};

export default IconButtonLink;
